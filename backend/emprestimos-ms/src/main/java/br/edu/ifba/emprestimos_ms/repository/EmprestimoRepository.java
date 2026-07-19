package br.edu.ifba.emprestimos_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;
import br.edu.ifba.emprestimos_ms.entity.StatusEmprestimo;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Regra 1: Conta quantos empréstimos ATIVOS o usuário possui
    @Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.usuarioId = :usuarioId AND e.dataDevolucao IS NULL")
    long countEmprestimosAtivos(@Param("usuarioId") Long usuarioId);

    // Regra 2 (CORRIGIDA): Verifica se o usuário possui alguma multa financeira em aberto
    @Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.usuarioId = :usuarioId AND e.valorMulta > 0 AND e.multaPaga = false")
    long countMultasPendentes(@Param("usuarioId") Long usuarioId);

    // Regra para impedir exclusão do livro se houver empréstimo em andamento
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Emprestimo e WHERE e.livroId = :livroId AND e.dataDevolucao IS NULL")
    boolean existsByLivroIdAndDataDevolucaoIsNull(@Param("livroId") Long livroId);

    List<Emprestimo> findByUsuarioId(Long usuarioId);

    List<Emprestimo> findByStatusAndDataPrevistaDevolucao(StatusEmprestimo status, LocalDate dataPrevistaDevolucao);
    
    List<Emprestimo> findByStatusAndDataPrevistaDevolucaoBefore(StatusEmprestimo status, LocalDate data);

    void deleteByUsuarioId(Long usuarioId);
}