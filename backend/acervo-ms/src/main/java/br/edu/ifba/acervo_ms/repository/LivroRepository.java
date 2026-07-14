package br.edu.ifba.acervo_ms.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.acervo_ms.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    Page<Livro> findByAutorContainingIgnoreCase(String autor, Pageable pageable);
    Livro findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}
