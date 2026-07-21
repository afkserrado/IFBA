package br.edu.ifba.email_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(

        @Email
        @NotBlank(message = "O destinatário é obrigatório.")
        String destinatario,

        @NotBlank(message = "O assunto é obrigatório.")
        String assunto,

        @NotBlank(message = "A mensagem é obrigatória.")
        String mensagem
) {
}