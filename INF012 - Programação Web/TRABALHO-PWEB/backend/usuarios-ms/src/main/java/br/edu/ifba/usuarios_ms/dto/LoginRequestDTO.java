package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String senha
) {
    
    public LoginRequestDTO(Usuario usuario) {
        this(
            usuario != null ? usuario.getEmail() : null,
            usuario != null ? usuario.getSenha() : null
        );
    }
}
