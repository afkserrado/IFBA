package br.edu.ifba.acervo_ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.acervo_ms.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    Livro findByIsbn(String isbn);
}
