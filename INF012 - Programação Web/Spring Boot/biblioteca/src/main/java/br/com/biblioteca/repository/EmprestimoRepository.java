package br.com.biblioteca.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
    // Verfica se um livro existe e se não foi devolvido
    public boolean existsByLivroIdAndDevolvidoFalse(Long id);

    // Lista empréstimos em atraso
    public List<Emprestimo> findByDevolvidoFalseAndDataPrevistaDevolucaoBefore(LocalDate data);
}
