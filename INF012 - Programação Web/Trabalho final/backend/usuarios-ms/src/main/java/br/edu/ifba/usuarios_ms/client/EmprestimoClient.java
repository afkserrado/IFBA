package br.edu.ifba.usuarios_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "emprestimos-ms",
    url = "${services.emprestimos.url}"
)
public interface EmprestimoClient {

    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/ativos")
    Boolean possuiEmprestimosAtivos(@PathVariable("id") Long usuarioId);

    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/multas")
    Boolean possuiMultasPendentes(@PathVariable("id") Long usuarioId);
}