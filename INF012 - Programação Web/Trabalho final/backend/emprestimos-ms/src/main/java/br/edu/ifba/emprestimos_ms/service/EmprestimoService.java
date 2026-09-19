package br.edu.ifba.emprestimos_ms.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.emprestimos_ms.client.AcervoClient;
import br.edu.ifba.emprestimos_ms.client.UsuarioClient;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequestDTO;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponseDTO;
import br.edu.ifba.emprestimos_ms.dto.DisponibilidadeResponse;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo;
import br.edu.ifba.emprestimos_ms.exception.EmprestimoNaoEncontradoException;
import br.edu.ifba.emprestimos_ms.exception.LivroNaoEncontradoException;
import br.edu.ifba.emprestimos_ms.exception.MultaPendenteException;
import br.edu.ifba.emprestimos_ms.exception.OperacaoNaoPermitidaException;
import br.edu.ifba.emprestimos_ms.exception.ServicoIndisponivelException;
import br.edu.ifba.emprestimos_ms.mapper.EmprestimoMapper;
import br.edu.ifba.emprestimos_ms.repository.EmprestimoRepository;
import feign.FeignException;
import br.edu.ifba.emprestimos_ms.dto.UsuarioResponseDTO;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoCriadoEvent;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoDevolvidoEvent;

@Service
public class EmprestimoService {

    private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.50");

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioClient usuarioClient;
    private final AcervoClient acervoClient;
    private final OutboxService outboxService;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            UsuarioClient usuarioClient,
            AcervoClient acervoClient,
            OutboxService outboxService
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioClient = usuarioClient;
        this.acervoClient = acervoClient;
        this.outboxService = outboxService;
    }

    // ##### MÉTODOS DE NEGÓCIO DE EMPRÉSTIMOS #####

    @Transactional
    public EmprestimoResponseDTO cadastrarEmprestimo(EmprestimoRequestDTO dto) {

        // Busca o usuário antes de modificar estoque ou persistir o empréstimo
        // Se usuarios-ms falhar ou o usuário não existir, nada foi alterado ainda
        UsuarioResponseDTO usuario = callUsuarioService(
                () -> usuarioClient.buscarUsuarioPorId(dto.getUsuarioId())
        );

        // Verifica multas pendentes internamente
        if (possuiMultasPendentes(dto.getUsuarioId())) {
            throw new MultaPendenteException(
                "O usuário possui multas pendentes e não pode realizar novos empréstimos."
            );
        }

        // Verifica disponibilidade e reduz estoque no acervo-ms
        DisponibilidadeResponse disponibilidade = callAcervoService(
            () -> acervoClient.estaDisponivel(dto.getLivroId())
        );

        if (!disponibilidade.disponivel()) {
            throw new OperacaoNaoPermitidaException("Livro sem exemplares disponíveis para empréstimo.");
        }

        boolean estoqueReduzido = false;

        try {
            callAcervoService(() -> {
                acervoClient.reduzirEstoque(dto.getLivroId());
                return null; // Supplier precisa retornar algo, mas não usamos o retorno
            });

            estoqueReduzido = true;

            // Salva o empréstimo
            Emprestimo emprestimo = EmprestimoMapper.converterDtoParaEntidade(dto);
            Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

            EmprestimoCriadoEvent evento = new EmprestimoCriadoEvent(
                    emprestimoSalvo.getId(),
                    emprestimoSalvo.getUsuarioId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    emprestimoSalvo.getLivroId(),
                    emprestimoSalvo.getDataEmprestimo(),
                    emprestimoSalvo.getDataPrevistaDevolucao()
            );

            outboxService.registrarEmprestimoCriado(evento);

            return EmprestimoMapper.converterEntidadeParaDto(emprestimoSalvo);
        }

        catch (RuntimeException ex) {
            if (estoqueReduzido) {
                compensarReducaoEstoque(dto.getLivroId(), ex);
            }

            throw ex;
        }
    }

    @Transactional
    public EmprestimoResponseDTO registrarDevolucao(@NonNull Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                "Empréstimo não encontrado com o ID: " + id
            ));

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new OperacaoNaoPermitidaException("Este empréstimo já foi devolvido anteriormente.");
        }

        // Busca os dados necessários à notificação antes de alterar
        // o empréstimo ou devolver o exemplar ao acervo.
        UsuarioResponseDTO usuario = callUsuarioService(
                () -> usuarioClient.buscarUsuarioPorId(emprestimo.getUsuarioId())
        );

        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);

        // Calcula multa, se houver atraso
        if (hoje.isAfter(emprestimo.getDataPrevistaDevolucao())) {
            long diasAtraso = ChronoUnit.DAYS.between(
                emprestimo.getDataPrevistaDevolucao(),
                hoje
            );

            BigDecimal multa = VALOR_MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso));
            emprestimo.setValorMulta(multa);
        }

        // Encerra o empréstimo como devolvido
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        boolean estoqueAumentado = false;

        try {
            // Atualiza o estoque do acervo
            callAcervoService(() -> {
                acervoClient.aumentarEstoque(emprestimo.getLivroId()); // método void
                return null;
            });

            estoqueAumentado = true;

            Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

            EmprestimoDevolvidoEvent evento = new EmprestimoDevolvidoEvent(
                emprestimoSalvo.getId(),
                emprestimoSalvo.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail(),
                emprestimoSalvo.getLivroId(),
                emprestimoSalvo.getDataDevolucao(),
                emprestimoSalvo.getValorMulta()
            );

            outboxService.registrarEmprestimoDevolvido(evento);

            return EmprestimoMapper.converterEntidadeParaDto(emprestimoSalvo);
        }

        catch (RuntimeException ex) {
            if (estoqueAumentado) {
                compensarAumentoEstoque(emprestimo.getLivroId(), ex);
            }

            throw ex;
        }
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> listarTodos() {
        return emprestimoRepository.findAll().stream()
            .map(EmprestimoMapper::converterEntidadeParaDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponseDTO> consultarPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId).stream()
            .map(EmprestimoMapper::converterEntidadeParaDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public boolean existeEmprestimoAtivoPorLivro(Long livroId) {
        return emprestimoRepository.existsByLivroIdAndStatus(
            livroId,
            StatusEmprestimo.ATIVO
        );
    }

    @Transactional
    public EmprestimoResponseDTO cancelarEmprestimo(@NonNull Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                "Empréstimo não encontrado com o ID: " + id
            ));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new OperacaoNaoPermitidaException(
                "Apenas empréstimos com status ATIVO podem ser cancelados."
            );
        }

        // Devolve o exemplar ao acervo
        callAcervoService(() -> {
            acervoClient.aumentarEstoque(emprestimo.getLivroId()); // método void
            return null;
        });

        emprestimo.setStatus(StatusEmprestimo.CANCELADO);
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimoRepository.save(emprestimo);

        return EmprestimoMapper.converterEntidadeParaDto(emprestimo);
    }

    @Transactional
    public EmprestimoResponseDTO pagarMulta(@NonNull Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                "Empréstimo não encontrado com o ID: " + id
            ));

        if (emprestimo.getValorMulta().compareTo(BigDecimal.ZERO) <= 0) {
            throw new OperacaoNaoPermitidaException(
                "Este empréstimo não possui multa a pagar."
            );
        }

        if (emprestimo.getMultaPaga()) {
            throw new OperacaoNaoPermitidaException(
                "Esta multa já foi paga."
            );
        }

        emprestimo.setMultaPaga(true);
        emprestimo.setDataAtualizacao(LocalDateTime.now());

        return EmprestimoMapper.converterEntidadeParaDto(
            emprestimoRepository.save(emprestimo)
        );
    }

    // Apenas para testes
    @Transactional
    public EmprestimoResponseDTO simularAtraso(@NonNull Long id, @NonNull LocalDate dataPrevistaDevolucao) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                "Empréstimo não encontrado com o ID: " + id
            ));

        emprestimo.setDataPrevistaDevolucao(dataPrevistaDevolucao);
        emprestimo.setDataAtualizacao(LocalDateTime.now());

        return EmprestimoMapper.converterEntidadeParaDto(
            emprestimoRepository.save(emprestimo)
        );
    }

    // ##### MÉTODOS AUXILIARES #####

    @Transactional(readOnly = true)
    public boolean possuiEmprestimosAtivos(Long usuarioId) {
        return emprestimoRepository.countEmprestimosAtivos(usuarioId) > 0;
    }

    @Transactional(readOnly = true)
    public boolean possuiMultasPendentes(Long usuarioId) {
        return emprestimoRepository.countMultasPendentes(usuarioId) > 0;
    }

    @Transactional
    public void limparRegistrosDeUsuarioDeletado(Long usuarioId) {
        emprestimoRepository.deleteByUsuarioId(usuarioId);
    }

    private void compensarReducaoEstoque(Long livroId, RuntimeException causaOriginal) {
        try {
            acervoClient.aumentarEstoque(livroId);
        } catch (Exception ex) {
            causaOriginal.addSuppressed(
                    new IllegalStateException(
                            "Falha ao compensar a redução de estoque do livro: " + livroId,
                            ex
                    )
            );
        }
    }

    private void compensarAumentoEstoque(Long livroId, RuntimeException causaOriginal) {
        try {
            acervoClient.reduzirEstoque(livroId);
        } catch (Exception ex) {
            causaOriginal.addSuppressed(
                    new IllegalStateException(
                            "Falha ao compensar o aumento de estoque do livro: " + livroId,
                            ex
                    )
            );
        }
    }

    // ##### MÉTODOS DE INTEGRAÇÃO COM TRATAMENTO PADRÃO #####

    /**
     * Envolve chamada ao usuarios-ms com tratamento padrão de exceções.
     */
    private <T> T callUsuarioService(Supplier<T> call) {
        try {
            return call.get();
        } catch (FeignException ex) {
            throw new ServicoIndisponivelException(
                "Serviço de usuários indisponível no momento.",
                ex
            );
        } catch (Exception ex) {
            throw new ServicoIndisponivelException(
                "Erro de comunicação com o serviço de usuários.",
                ex
            );
        }
    }

    /**
     * Envolve chamada ao acervo-ms com tratamento padrão de exceções.
     */
    private <T> T callAcervoService(Supplier<T> call) {
        
        try {
            return call.get();
        } 
        
        catch (FeignException.NotFound ex) {
            throw new LivroNaoEncontradoException(
                "Livro não encontrado no serviço de acervo."
            );
        } 
        
        catch (FeignException ex) {
            throw new ServicoIndisponivelException(
                "Serviço de acervo indisponível no momento.",
                ex
            );
        }
        
        catch (Exception ex) {
            throw new ServicoIndisponivelException(
                "Erro de comunicação com o serviço de acervo.",
                ex
            );
        }
    }
}