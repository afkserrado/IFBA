package br.edu.ifba.usuarios_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "emprestimos-ms",
    url = "${microservice.emprestimos.url}"
)
public interface EmprestimoClient {

    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/ativos")
    boolean possuiEmprestimosAtivos(@PathVariable("id") Long usuarioId);

    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/multas")
    boolean possuiMultasPendentes(@PathVariable("id") Long usuarioId);
}