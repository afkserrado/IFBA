package br.edu.ifba.emprestimos_ms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.ifba.emprestimos_ms.entity.Emprestimo;


@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Regra 1: Conta quantos empréstimos ATIVOS o usuário possui (onde a data de devolução real está nula)
    @Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.usuarioId = :usuarioId AND e.dataDevolucaoReal IS NULL")
    long countEmprestimosAtivos(@Param("usuarioId") Long usuarioId);

    // Regra 2: Verifica se o usuário possui alguma multa financeira em aberto/atraso
    @Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.usuarioId = :usuarioId AND e.multaPendente = true")
    long countMultasPendentes(@Param("usuarioId") Long usuarioId);

    void deleteByUsuarioId(Long usuarioId);
}
