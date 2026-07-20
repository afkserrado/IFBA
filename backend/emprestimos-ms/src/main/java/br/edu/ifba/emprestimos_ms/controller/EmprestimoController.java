package br.edu.ifba.emprestimos_ms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/emprestimos")
@Tag(name = "Empréstimos", description = "Endpoints para gerenciamento do ciclo de vida de empréstimos de livros")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and (hasRole('ADMIN') or #request.usuarioId == authentication.principal.id)")
    @Operation(summary = "Registra um novo empréstimo", description = "Cria um registro de empréstimo validando a disponibilidade do livro e situação do usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empréstimo registrado com sucesso",
            content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos ou livro indisponível", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acesso negado ou tentativa de criar empréstimo para outro usuário", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário ou Livro não localizados", content = @Content)
    })
    public ResponseEntity<EmprestimoResponse> cadastrar(@Valid @RequestBody EmprestimoRequest request) {
        EmprestimoResponse response = emprestimoService.cadastrarEmprestimo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/devolucao")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Registra a devolução de um livro", description = "Finaliza um empréstimo ativo mudando seu status e atualizando o estoque do acervo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolução registrada com sucesso",
            content = @Content(schema = @Schema(implementation = EmprestimoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Empréstimo já foi devolvido anteriormente", content = @Content),
        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content),
        @ApiResponse(responseCode = "404", description = "Identificador de empréstimo não encontrado", content = @Content)
    })
    public ResponseEntity<EmprestimoResponse> devolver(
        @Parameter(description = "ID do empréstimo a ser encerrado") @PathVariable Long id
    ) {
        EmprestimoResponse response = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Lista todos os empréstimos", description = "Retorna o histórico completo de todos os empréstimos registrados no microsserviço.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso"),
        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content)
    })
    public ResponseEntity<List<EmprestimoResponse>> listarTodos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    @PreAuthorize("hasRole('ADMIN') or #usuarioId == authentication.principal.id")
    @Operation(summary = "Consulta empréstimos de um usuário", description = "Retorna todos os empréstimos (ativos e encerrados) vinculados a um usuário específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empréstimos do usuário gerada"),
        @ApiResponse(responseCode = "403", description = "Acesso negado para o histórico deste usuário", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não localizado", content = @Content)
    })
    public ResponseEntity<List<EmprestimoResponse>> consultarPorUsuario(
        @Parameter(description = "ID do usuário consultado") @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(emprestimoService.consultarPorUsuario(usuarioId));
    }

    @GetMapping("/livros/{livroId}/ativos/existe")
    @PreAuthorize("hasAnyRole('ADMIN', 'SYSTEM') or isAuthenticated()")
    @Operation(summary = "Verifica empréstimo ativo por livro", description = "Consulta rápida para checar se uma unidade do livro informado está atualmente emprestada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verificação realizada com sucesso"),
        @ApiResponse(responseCode = "401", description = "Usuário ou serviço não autenticado", content = @Content)
    })
    public ResponseEntity<Boolean> existeEmprestimoAtivoPorLivro(
        @Parameter(description = "ID do livro avaliado") @PathVariable Long livroId
    ) {
        boolean existe = emprestimoService.existeEmprestimoAtivoPorLivro(livroId);
        return ResponseEntity.ok(existe);
    }
}
