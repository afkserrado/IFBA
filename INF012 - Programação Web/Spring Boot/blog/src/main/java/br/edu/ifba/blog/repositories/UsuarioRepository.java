package br.edu.ifba.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.blog.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByNome(String usuario);
}
