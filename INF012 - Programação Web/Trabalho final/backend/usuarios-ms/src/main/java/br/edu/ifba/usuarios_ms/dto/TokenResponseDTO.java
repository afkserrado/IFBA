package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.entity.Usuario;

public record TokenResponseDTO(
    String token,
    String tipo,
    UsuarioResponseDTO usuario
) {

    public TokenResponseDTO(Usuario usuario, String token) {
        this(
            token,
            "Bearer",
            usuario != null ? new UsuarioResponseDTO(usuario) : null
        );
    }
}
