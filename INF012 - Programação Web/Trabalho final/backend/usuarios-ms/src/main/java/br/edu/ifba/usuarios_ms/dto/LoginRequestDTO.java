package br.edu.ifba.usuarios_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Dados enviados pelo cliente para realizar login
public record LoginRequestDTO(

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String senha
) {}