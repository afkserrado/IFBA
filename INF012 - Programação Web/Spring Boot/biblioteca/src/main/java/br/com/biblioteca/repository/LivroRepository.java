package br.com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.biblioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    public List<Livro> findByAutorContainingIgnoreCase(String autor);
    public List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
