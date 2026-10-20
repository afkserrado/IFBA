// src/main/java/br/edu/ifba/notificacoes_ms/client/UsuariosClient.java
package br.edu.ifba.notificacoes_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifba.notificacoes_ms.dto.LoginRequestDTO;
import br.edu.ifba.notificacoes_ms.dto.LoginResponseDTO;

@FeignClient(
    name = "usuarios-ms",
    url = "${microservice.usuarios.url}"
)
public interface UsuariosClient {

    @PostMapping("/api/v1/auth/login")
    LoginResponseDTO login(@RequestBody LoginRequestDTO request);
}