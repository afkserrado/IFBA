package br.edu.ifba.emprestimos_ms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifba.emprestimos_ms.service.EmprestimoService; 

@RestController
@RequestMapping("/api/v1/emprestimos/validar-exclusao")
public class ValidaExclusaoController {

    private final EmprestimoService emprestimoService;

    public ValidaExclusaoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // Endpoint acessado pelo Feign para checar livros pendentes
    @GetMapping("/usuario/{id}/ativos")
    public ResponseEntity<Boolean> possuiEmprestimosAtivos(@PathVariable Long id) {
        boolean temAtivos = emprestimoService.possuiEmprestimosAtivos(id);
        return ResponseEntity.ok(temAtivos);
    }

    // Endpoint acessado pelo Feign para checar débitos financeiros
    @GetMapping("/usuario/{id}/multas")
    public ResponseEntity<Boolean> possuiMultasPendentes(@PathVariable Long id) {
        boolean temMultas = emprestimoService.possuiMultasPendentes(id);
        return ResponseEntity.ok(temMultas);
    }
}
