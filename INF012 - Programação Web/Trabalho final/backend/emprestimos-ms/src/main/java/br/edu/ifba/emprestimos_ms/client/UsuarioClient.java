package br.edu.ifba.emprestimos_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifba.emprestimos_ms.dto.UsuarioResponseDTO;

@FeignClient(
    name = "usuarios-ms",
    url = "${services.usuarios.url}"
)
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}/validar-situacao")
    Boolean validarSituacaoCadastral(@PathVariable("id") Long id);

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioResponseDTO buscarUsuarioPorId(@PathVariable("id") Long id);
}