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

@Service
public class EmprestimoService {

    private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.50");

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioClient usuarioClient;
    private final AcervoClient acervoClient;

    public EmprestimoService(
        EmprestimoRepository emprestimoRepository,
        UsuarioClient usuarioClient,
        AcervoClient acervoClient
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioClient = usuarioClient;
        this.acervoClient = acervoClient;
    }

    // ##### MÉTODOS DE NEGÓCIO DE EMPRÉSTIMOS #####

    @Transactional
    public EmprestimoResponseDTO cadastrarEmprestimo(EmprestimoRequestDTO dto) {

        // Valida situação cadastral do usuário via usuarios-ms
        boolean usuarioValido = callUsuarioService(
            () -> usuarioClient.validarSituacaoCadastral(dto.getUsuarioId())
        );

        if (!usuarioValido) {
            throw new IllegalStateException("Usuário não cadastrado ou situação cadastral inválida.");
        }

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

        callAcervoService(() -> {
            acervoClient.reduzirEstoque(dto.getLivroId());
            return null; // Supplier precisa retornar algo, mas não usamos o retorno
        });

        // Salva o empréstimo
        Emprestimo emprestimo = EmprestimoMapper.converterDtoParaEntidade(dto);
        emprestimoRepository.save(emprestimo);

        return EmprestimoMapper.converterEntidadeParaDto(emprestimo);
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

        // Atualiza o estoque do acervo
        callAcervoService(() -> {
            acervoClient.aumentarEstoque(emprestimo.getLivroId()); // método void
            return null;
        });

        emprestimoRepository.save(emprestimo);

        return EmprestimoMapper.converterEntidadeParaDto(emprestimo);
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