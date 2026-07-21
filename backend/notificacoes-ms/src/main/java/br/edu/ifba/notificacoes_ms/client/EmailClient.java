package br.edu.ifba.notificacoes_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifba.notificacoes_ms.dto.EmailRequest;

@FeignClient(
    name = "email-ms",
    url = "${microservice.email.url}"
)
public interface EmailClient {

    @PostMapping("/emails/enviar")
    void enviarEmail(
        @RequestBody EmailRequest request
    );
}

