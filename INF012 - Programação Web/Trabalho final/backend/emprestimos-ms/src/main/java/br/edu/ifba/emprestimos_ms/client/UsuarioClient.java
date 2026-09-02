package br.edu.ifba.emprestimos_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-ms", url = "${microservice.usuarios.url}")
public interface UsuarioClient {
	@GetMapping("/api/v1/usuarios/{id}/validar-situacao")
    boolean validarSituacaoCadastral(@PathVariable("id") Long id);
}
