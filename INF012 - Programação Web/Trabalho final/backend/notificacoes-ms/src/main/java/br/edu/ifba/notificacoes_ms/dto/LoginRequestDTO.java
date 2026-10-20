// src/main/java/br/edu/ifba/notificacoes_ms/dto/LoginRequest.java
package br.edu.ifba.notificacoes_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @Email
        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha
) {
}