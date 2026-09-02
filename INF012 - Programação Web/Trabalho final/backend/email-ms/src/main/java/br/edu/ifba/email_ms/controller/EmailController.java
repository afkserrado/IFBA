package br.edu.ifba.email_ms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.email_ms.dto.EmailRequest;
import br.edu.ifba.email_ms.service.EmailService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/emails")
@Tag(name = "E-mails", description = "Endpoints para envio de e-mails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    @Operation(
        summary = "Envia um e-mail",
        description = "Envia uma mensagem de e-mail utilizando o serviço SMTP configurado."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "E-mail enviado com sucesso"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Dados da requisição inválidos",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro ao enviar o e-mail",
            content = @Content
        )
    })
    public ResponseEntity<EmailRequest> enviarEmail(
            @RequestBody @Valid EmailRequest request
    ) {
        emailService.enviarEmail(request);
        return ResponseEntity.ok(request);
    }
}