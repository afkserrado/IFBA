package br.edu.ifba.blog.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.edu.ifba.blog.dtos.EmailDto;

@FeignClient("email-ms") // Indica que este cliente chama o microsserviço email-ms
public interface EmailClient {
    
    @RequestMapping(
        method = RequestMethod.POST, // Define que a chamada será feita por POST
        value = "/email/send" // Define o endpoint chamado no Email Service
    )
    public ResponseEntity<EmailDto> sendEmail(
        @RequestBody EmailDto dto // Envia os dados do e-mail no corpo da requisição
    );
}
