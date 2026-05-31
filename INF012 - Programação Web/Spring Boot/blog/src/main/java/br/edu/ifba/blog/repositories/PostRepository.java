package br.edu.ifba.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifba.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    public List<Post> findByTitulo(String titulo);
    public List<Post> findByUsuarioNome(String nome);
    public List<Post> findByTituloContaining(String titulo);
    public List<Post> findByTituloStartsWith(String titulo);

    @Query("""
        SELECT p
        FROM posts p
        WHERE p.titulo LIKE CONCAT('%', :titulo, '%')
        """)
    List<Post> searchByTitleLike(@Param("titulo") String titulo);
}
