package br.com.biblioteca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    public List<Livro> findByAutorContainingIgnoreCase(String autor);
    public List<Livro> findByTituloContainingIgnoreCase(String titulo);
    public Page<Livro> findByAutorContainingIgnoreCase(String autor, Pageable pageable);
    public Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    
    @Query(
        "SELECT COALESCE(SUM(l.quantidadeDisponivel), 0) FROM Livro l"
    )
    public Long sumByQuantidadeDisponivel();
}
