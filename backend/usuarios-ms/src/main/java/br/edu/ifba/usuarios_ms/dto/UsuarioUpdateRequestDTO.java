package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequestDTO(
    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    String email,

    @NotBlank(message = "O papel (role) do usuário é obrigatório.")
    String role
) {
    // Construtor alternativo que aceita a Entidade
    public UsuarioUpdateRequestDTO(Usuario usuario) {
        this(
            usuario != null ? usuario.getNome() : null,
            usuario != null ? usuario.getEmail() : null,
            usuario != null ? usuario.getRole() : null
        );
    }
}

