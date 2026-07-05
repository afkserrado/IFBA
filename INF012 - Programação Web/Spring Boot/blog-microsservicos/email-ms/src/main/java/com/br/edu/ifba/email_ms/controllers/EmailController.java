package com.br.edu.ifba.email_ms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.edu.ifba.email_ms.dtos.EmailDto;
import com.br.edu.ifba.email_ms.model.Email;
import com.br.edu.ifba.email_ms.services.EmailService;

@RestController // Define a classe como um controller REST
@RequestMapping("/email") // Define a rota base do controller
public class EmailController {
    
    private EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/send") // Define o endpoint POST /email/send
    public ResponseEntity<EmailDto> sendEmail(
        @RequestBody EmailDto data // Lê o JSON do body e converte para EmailDTO
    ) {

        Email email = service.sendEmail(data); // Chama o serviço responsável por enviar o e-mail

        return new ResponseEntity<>(
            new EmailDto(email),
            HttpStatus.CREATED // Retorna HTTP 201 Created
        );
    }
}
