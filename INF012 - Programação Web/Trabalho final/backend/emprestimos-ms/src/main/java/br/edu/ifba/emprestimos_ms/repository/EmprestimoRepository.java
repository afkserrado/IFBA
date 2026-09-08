package br.edu.ifba.emprestimos_ms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Regra para contar quantos empréstimos ATIVOS o usuário possui
    @Query("""
        SELECT COUNT(e)
        FROM Emprestimo e
        WHERE e.usuarioId = :usuarioId
          AND e.status = br.edu.ifba.emprestimos_ms.enums.StatusEmprestimo.ATIVO
    """)
    long countEmprestimosAtivos(Long usuarioId);

    // Regra para verificar se o usuário possui alguma multa financeira em aberto
    @Query("""
        SELECT COUNT(e)
        FROM Emprestimo e
        WHERE e.usuarioId = :usuarioId
          AND e.valorMulta > 0
          AND e.multaPaga = false
    """)
    long countMultasPendentes(Long usuarioId);

    // Regra para impedir exclusão do livro se houver empréstimo ativo
    boolean existsByLivroIdAndStatus(Long livroId, StatusEmprestimo status);

    List<Emprestimo> findByUsuarioId(Long usuarioId);

    List<Emprestimo> findByStatusAndDataPrevistaDevolucao(
        StatusEmprestimo status,
        LocalDate dataPrevistaDevolucao
    );

    List<Emprestimo> findByStatusAndDataPrevistaDevolucaoBefore(
        StatusEmprestimo status,
        LocalDate data
    );

    void deleteByUsuarioId(Long usuarioId);
}