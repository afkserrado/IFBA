package br.edu.ifba.emprestimos_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "acervo-ms", url = "${microservice.acervo.url}")
public interface AcervoClient {
	@GetMapping("/api/v1/livros/{id}/disponibilidade")
    boolean estaDisponivel(@PathVariable("id") Long id);

    @PostMapping("/api/v1/livros/{id}/reduzir-estoque")
    void reduzirEstoque(@PathVariable("id") Long id);

    @PostMapping("/api/v1/livros/{id}/aumentar-estoque")
    void aumentarEstoque(@PathVariable("id") Long id);
}
