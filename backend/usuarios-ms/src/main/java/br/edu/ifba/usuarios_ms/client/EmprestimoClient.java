package br.edu.ifba.usuarios_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "emprestimos-ms", url = "${microservice.emprestimos.url}")
public interface EmprestimoClient {

    // GET síncrono para http://localhost:8082/api/v1/emprestimos/validar-exclusao/usuario/{id}/ativos
    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/ativos")
    boolean possuiEmprestimosAtivos(@PathVariable("id") Long usuarioId);

    // GET síncrono para http://localhost:8082/api/v1/emprestimos/validar-exclusao/usuario/{id}/multas
    @GetMapping("/api/v1/emprestimos/validar-exclusao/usuario/{id}/multas")
    boolean possuiMultasPendentes(@PathVariable("id") Long usuarioId);
}
