package br.edu.ifba.emprestimos_ms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifba.emprestimos_ms.service.EmprestimoService; 

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/emprestimos/validar-exclusao")
@Tag(name = "Validação de Exclusão (Inter-módulos)", description = "Endpoints internos consumidos via Feign Client para validação de integridade antes da exclusão de dados")
public class ValidaExclusaoController {

    private final EmprestimoService emprestimoService;

    public ValidaExclusaoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/usuario/{id}/ativos")
    @Operation(
        summary = "Verifica se o usuário possui empréstimos pendentes de devolução", 
        description = "Consumido internamente pelo microsserviço de usuários (via Feign) para impedir a exclusão de contas com pendências de acervo."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso. Retorna true se houver empréstimos ativos."),
        @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas o microsserviço de usuários ou administradores podem consultar.", content = @Content)
    })
    public ResponseEntity<Boolean> possuiEmprestimosAtivos(
        @Parameter(description = "ID do usuário que deseja se excluir") @PathVariable Long id,
        HttpServletRequest request
    ) {
        System.out.println("AUTH HEADER: "
        + request.getHeader("Authorization"));
        boolean temAtivos = emprestimoService.possuiEmprestimosAtivos(id);
        return ResponseEntity.ok(temAtivos);
    }

    @GetMapping("/usuario/{id}/multas")
    @Operation(
        summary = "Verifica se o usuário possui multas financeiras em aberto", 
        description = "Consumido internamente pelo microsserviço de usuários (via Feign) para travar a exclusão de contas com débitos pendentes."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso. Retorna true se houver multas em aberto."),
        @ApiResponse(responseCode = "403", description = "Acesso negado. Apenas o microsserviço de usuários ou administradores podem consultar.", content = @Content)
    })
    public ResponseEntity<Boolean> possuiMultasPendentes(
        @Parameter(description = "ID do usuário sob análise de exclusão") @PathVariable Long id,
        HttpServletRequest request
    ) {
        System.out.println("AUTH HEADER: "
        + request.getHeader("Authorization"));
        boolean temMultas = emprestimoService.possuiMultasPendentes(id);
        return ResponseEntity.ok(temMultas);
    }
}
