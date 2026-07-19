package br.edu.ifba.emprestimos_ms.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.emprestimos_ms.client.AcervoClient;
import br.edu.ifba.emprestimos_ms.client.UsuarioClient;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequest;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponse;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.entity.StatusEmprestimo;
import br.edu.ifba.emprestimos_ms.exception.EmprestimoNaoEncontradoException;
import br.edu.ifba.emprestimos_ms.exception.MultaPendenteException;
import br.edu.ifba.emprestimos_ms.mapper.EmprestimoMapper;
import br.edu.ifba.emprestimos_ms.repository.EmprestimoRepository;

@Service
public class EmprestimoService {
	private static final BigDecimal VALOR_MULTA_DIARIA = new BigDecimal("2.50");

    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;
    private final UsuarioClient usuarioClient;
    private final AcervoClient acervoClient;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             EmprestimoMapper emprestimoMapper,
                             UsuarioClient usuarioClient,
                             AcervoClient acervoClient) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.usuarioClient = usuarioClient;
        this.acervoClient = acervoClient;
    }

    // --- SEUS MÉTODOS ORIGINAIS ---

    private boolean mapToBoolean(long count) {
        return count > 0;
    }

    @Transactional(readOnly = true)
    public boolean possuiEmprestimosAtivos(Long usuarioId) {
        return emprestimoRepository.countEmprestimosAtivos(usuarioId) > 0;
    }

    @Transactional(readOnly = true)
    public boolean possuiMultasPendentes(Long usuarioId) {
        return mapToBoolean(emprestimoRepository.countMultasPendentes(usuarioId));
    }

    @Transactional
    public void limparRegistrosDeUsuarioDeletado(Long usuarioId) {
        emprestimoRepository.deleteByUsuarioId(usuarioId);
    }

    // --- MÉTODOS DE NEGÓCIO DE EMPRÉSTIMOS ---

    @Transactional
    public EmprestimoResponse cadastrarEmprestimo(EmprestimoRequest request) {
        // 1. Confirma a existência e situação cadastral do usuário via OpenFeign
        try {
            if (!usuarioClient.validarSituacaoCadastral(request.usuarioId())) {
                throw new IllegalStateException("Usuário em situação irregular ou bloqueado.");
            }
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao consultar serviço de usuários ou serviço indisponível.");
        }

        // 2. Verifica internamente se o usuário possui multas pendentes
        if (possuiMultasPendentes(request.usuarioId())) {
            throw new MultaPendenteException("O usuário possui multas pendentes e não pode realizar novos empréstimos.");
        }

        // 3. Consulta e atualiza o acervo via OpenFeign
        try {
            if (!acervoClient.estaDisponivel(request.livroId())) {
                throw new IllegalStateException("Livro sem exemplares disponíveis para empréstimo.");
            }
            acervoClient.reduzirEstoque(request.livroId());
        } catch (IllegalStateException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao comunicar com o acervo para retirada do exemplar.");
        }

        // 4. Cadastra o empréstimo no banco
        Emprestimo emprestimo = emprestimoMapper.toEntity(request);
        emprestimo = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toResponse(emprestimo);
    }

    @Transactional
    public EmprestimoResponse registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new EmprestimoNaoEncontradoException("Empréstimo não encontrado com o ID: " + id));

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException("Este empréstimo já foi devolvido anteriormente.");
        }

        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);

        // Verifica atraso para calcular multa
        if (hoje.isAfter(emprestimo.getDataPrevistaDevolucao())) {
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevistaDevolucao(), hoje);
            BigDecimal multa = VALOR_MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso));
            emprestimo.setValorMulta(multa);
            emprestimo.setStatus(StatusEmprestimo.ATRASADO);
        } else {
            emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);
        }

        // Solicita o retorno do exemplar ao acervo via OpenFeign
        try {
            acervoClient.aumentarEstoque(emprestimo.getLivroId());
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao notificar devolução do exemplar no acervo.");
        }

        emprestimo = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toResponse(emprestimo);
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponse> listarTodos() {
        return emprestimoRepository.findAll().stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EmprestimoResponse> consultarPorUsuario(Long usuarioId) {
        return emprestimoRepository.findByUsuarioId(usuarioId).stream()
            .map(emprestimoMapper::toResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean existeEmprestimoAtivoPorLivro(Long livroId) {
        return emprestimoRepository.existsByLivroIdAndDataDevolucaoIsNull(livroId);
    }

    // Processo automático para verificar diariamente quem não devolveu o livro no prazo e virar o status para ATRASADO
    @Scheduled(cron = "0 0 8 * * *")
    @Transactional
    public void processarAtrasos() {
        LocalDate hoje = LocalDate.now();

        List<Emprestimo> vencidos = emprestimoRepository.findByStatusAndDataPrevistaDevolucaoBefore(StatusEmprestimo.ATIVO, hoje);
        for (Emprestimo e : vencidos) {
            e.setStatus(StatusEmprestimo.ATRASADO);
            emprestimoRepository.save(e);
        }
    }
}
    
    
