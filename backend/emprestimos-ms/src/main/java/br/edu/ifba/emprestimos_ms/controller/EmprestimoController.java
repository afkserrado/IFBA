package br.edu.ifba.emprestimos_ms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequest;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponse;
import br.edu.ifba.emprestimos_ms.service.EmprestimoService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/emprestimos")
public class EmprestimoController {
	private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponse> cadastrar(@Valid @RequestBody EmprestimoRequest request) {
        EmprestimoResponse response = emprestimoService.cadastrarEmprestimo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoResponse> devolver(@PathVariable Long id) {
        EmprestimoResponse response = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponse>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EmprestimoResponse>> consultarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(emprestimoService.consultarPorUsuario(usuarioId));
    }

    @GetMapping("/livros/{livroId}/ativos/existe")
    public ResponseEntity<Boolean> existeEmprestimoAtivoPorLivro(@PathVariable Long livroId) {
        boolean existe = emprestimoService.existeEmprestimoAtivoPorLivro(livroId);
        return ResponseEntity.ok(existe);
    }
}
