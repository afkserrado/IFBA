package br.edu.ifba.emprestimos_ms.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifba.emprestimos_ms.client.AcervoClient;
import br.edu.ifba.emprestimos_ms.client.UsuarioClient;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequest;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponse;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.entity.StatusEmprestimo;
import br.edu.ifba.emprestimos_ms.exception.BusinessException;
import br.edu.ifba.emprestimos_ms.exception.EmprestimoNaoEncontradoException;
import br.edu.ifba.emprestimos_ms.exception.MultaPendenteException;
import br.edu.ifba.emprestimos_ms.mapper.EmprestimoMapper;
import br.edu.ifba.emprestimos_ms.repository.EmprestimoRepository;
import br.edu.ifba.security.jwt.AuthenticatedUser;

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

    // --- MÉTODOS AUXILIARES ---

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

    // MÉTODOS DE NEGÓCIO DE EMPRÉSTIMOS

    @Transactional
    public EmprestimoResponse cadastrarEmprestimo(EmprestimoRequest request) {

        System.out.println("===== INICIO CADASTRO EMPRESTIMO =====");
        System.out.println("Usuario ID: " + request.usuarioId());
        System.out.println("Livro ID: " + request.livroId());

        // Confirma a existência e situação cadastral do usuário via OpenFeign
        try {
            System.out.println("Consultando usuario-ms...");

            boolean usuarioValido = usuarioClient.validarSituacaoCadastral(request.usuarioId());

            System.out.println("Resposta usuario-ms: " + usuarioValido);

            if (!usuarioValido) {
                System.out.println("Usuario invalido.");
                throw new IllegalStateException("Usuário não cadastrado.");
            }

            System.out.println("Usuario validado com sucesso.");

        } catch (IllegalStateException e) {
            System.out.println("Erro de regra usuario: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            System.out.println("Erro comunicando com usuario-ms:");
            e.printStackTrace();

            throw new IllegalStateException(
                    "Erro ao consultar serviço de usuários ou serviço indisponível.");
        }

        // Verifica internamente se o usuário possui multas pendentes
        System.out.println("Verificando multas pendentes...");

        boolean possuiMulta = possuiMultasPendentes(request.usuarioId());

        System.out.println("Possui multa: " + possuiMulta);

        if (possuiMulta) {
            System.out.println("Emprestimo bloqueado por multa.");

            throw new MultaPendenteException(
                    "O usuário possui multas pendentes e não pode realizar novos empréstimos.");
        }

        // Consulta e atualiza o acervo via OpenFeign
        try {

            System.out.println("Consultando disponibilidade no acervo-ms...");

            var disponibilidade = acervoClient.estaDisponivel(request.livroId());

            System.out.println(
                    "Resposta acervo disponibilidade: "
                            + disponibilidade.disponivel());

            if (!disponibilidade.disponivel()) {

                System.out.println("Livro sem estoque.");

                throw new IllegalStateException(
                        "Livro sem exemplares disponíveis para empréstimo.");
            }

            System.out.println("Livro disponível.");

            System.out.println("Chamando reduzirEstoque no acervo-ms...");

            acervoClient.reduzirEstoque(request.livroId());

            System.out.println("Estoque reduzido com sucesso.");

        } catch (IllegalStateException e) {

            System.out.println(
                    "Erro de regra no acervo: " + e.getMessage());

            throw e;

        } catch (Exception e) {

            System.out.println("Erro comunicando com acervo-ms:");

            e.printStackTrace();

            throw new IllegalStateException(
                    "Erro ao comunicar com o acervo para retirada do exemplar.");
        }

        // Cadastra o empréstimo no banco
        System.out.println("Salvando emprestimo no banco...");

        Emprestimo emprestimo = emprestimoMapper.toEntity(request);

        emprestimo = emprestimoRepository.save(emprestimo);

        System.out.println(
                "Emprestimo salvo. ID: " + emprestimo.getId());

        System.out.println("===== FIM CADASTRO EMPRESTIMO =====");

        return emprestimoMapper.toResponse(emprestimo);
    }

    @Transactional
    public EmprestimoResponse registrarDevolucao(Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                        "Empréstimo não encontrado com o ID: " + id));

        if (emprestimo.getStatus() == StatusEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException("Este empréstimo já foi devolvido anteriormente.");
        }

        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);

        // Calcula multa, se houver atraso
        if (hoje.isAfter(emprestimo.getDataPrevistaDevolucao())) {
            long diasAtraso = ChronoUnit.DAYS.between(
                    emprestimo.getDataPrevistaDevolucao(),
                    hoje);

            BigDecimal multa = VALOR_MULTA_DIARIA.multiply(BigDecimal.valueOf(diasAtraso));
            emprestimo.setValorMulta(multa);
        }

        // Sempre encerra o empréstimo como devolvido
        emprestimo.setStatus(StatusEmprestimo.DEVOLVIDO);

        // Atualiza o estoque do acervo
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

        return emprestimoRepository.existsByLivroIdAndStatus(
                livroId,
                StatusEmprestimo.ATIVO);
    }

    @Transactional
    public EmprestimoResponse cancelarEmprestimo(Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNaoEncontradoException(
                        "Empréstimo não encontrado com o ID: " + id));

        if (emprestimo.getStatus() != StatusEmprestimo.ATIVO) {
            throw new IllegalStateException(
                    "Apenas empréstimos com status ATIVO podem ser cancelados.");
        }

        // Devolve o exemplar ao acervo
        try {
            acervoClient.aumentarEstoque(emprestimo.getLivroId());
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Erro ao notificar o acervo sobre o cancelamento do empréstimo.");
        }

        emprestimo.setStatus(StatusEmprestimo.CANCELADO);
        emprestimo.setDataDevolucao(LocalDate.now());
        emprestimo = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toResponse(emprestimo);
    }

    // Processo automático para verificar diariamente quem não devolveu o livro no
    // prazo e virar o status para ATRASADO
    @Scheduled(cron = "0 0 8 * * *")
    @Transactional
    public void processarAtrasos() {
        LocalDate hoje = LocalDate.now();

        List<Emprestimo> vencidos = emprestimoRepository
                .findByStatusAndDataPrevistaDevolucaoBefore(StatusEmprestimo.ATIVO, hoje);
        for (Emprestimo e : vencidos) {
            e.setStatus(StatusEmprestimo.ATRASADO);
            emprestimoRepository.save(e);
        }
    }
}
