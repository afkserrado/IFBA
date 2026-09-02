package br.edu.ifba.usuarios_ms.dto;

import java.time.LocalDateTime;

import br.edu.ifba.usuarios_ms.entity.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String cpf,
    String nome,
    String email,
    String role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    // Construtor alternativo que extrai dados da Entidade Usuario
    public UsuarioResponseDTO(Usuario usuario) {
        this(
            usuario != null ? usuario.getId() : null,
            usuario != null ? usuario.getCpf() : null,
            usuario != null ? usuario.getNome() : null,
            usuario != null ? usuario.getEmail() : null,
            usuario != null ? usuario.getRole() : null,
            usuario != null ? usuario.getCreatedAt() : null,
            usuario != null ? usuario.getUpdatedAt() : null
        );
    }
}
