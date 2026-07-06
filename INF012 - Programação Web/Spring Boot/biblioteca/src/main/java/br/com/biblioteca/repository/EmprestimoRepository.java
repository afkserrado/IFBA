package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    
    public boolean existsByLivroIdAndDevolvidoFalse(Long id);
}
