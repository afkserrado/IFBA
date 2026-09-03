package br.edu.ifba.acervo_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "emprestimos-ms", 
    url = "${services.emprestimos.url}"
)
public interface EmprestimoClient {
    @GetMapping("/api/v1/emprestimos/livros/{livroId}/ativos/existe")
    Boolean existeEmprestimoAtivoPorLivro(@PathVariable("livroId") Long livroId);
}
