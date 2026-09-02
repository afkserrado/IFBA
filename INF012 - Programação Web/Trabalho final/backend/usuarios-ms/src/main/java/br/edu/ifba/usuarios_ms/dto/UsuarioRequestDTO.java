package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
    @NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos.")
    String cpf,

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome,

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    String senha,

    @NotBlank(message = "O papel (role) do usuário é obrigatório.")
    String role
) {
    // Construtor alternativo que aceita a Entidade
    public UsuarioRequestDTO(Usuario usuario) {
        this(
            usuario != null ? usuario.getCpf() : null,
            usuario != null ? usuario.getNome() : null,
            usuario != null ? usuario.getEmail() : null,
            usuario != null ? usuario.getSenha() : null,
            usuario != null ? usuario.getRole() : null

        );
    }
}

