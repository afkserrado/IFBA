package br.com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.biblioteca.dto.EmprestimoRequestDto;
import br.com.biblioteca.dto.EmprestimoResponseDto;
import br.com.biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @Operation(summary = "Registrar empréstimo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Empréstimo registrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Livro indisponível")
    })
    @PostMapping
    public ResponseEntity<EmprestimoRequestDto> registrarEmprestimo(
        @RequestBody @Valid EmprestimoRequestDto dto,
        UriComponentsBuilder uriBuilder
    ) {

        EmprestimoRequestDto emprestimoSalvo = emprestimoService.registrarEmprestimo(dto);

        URI uri = uriBuilder
                    .path("/emprestimos/{id}")
                    .buildAndExpand(emprestimoSalvo.getId())
                    .toUri();

        return ResponseEntity
                .created(uri)
                .body(emprestimoSalvo);
    }

    @Operation(summary = "Listar empréstimos")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empréstimos listados")
    })
    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDto>> listarEmprestimos() {
        return ResponseEntity
                .ok(emprestimoService.listarEmprestimos());
    }

    @Operation(summary = "Buscar empréstimo por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empréstimo encontrado"),
        @ApiResponse(responseCode = "404", description = "Id inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDto> buscarEmprestimoPorId(
        @PathVariable Long id
    ) {

        return ResponseEntity
                .ok(emprestimoService.buscarEmprestimoPorId(id));
    }

    @Operation(summary = "Listar empréstimos em atraso")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empréstimos em atraso listados")
    })
    @GetMapping("/atrasados")
    public ResponseEntity<List<EmprestimoResponseDto>> listarEmprestimosEmAtraso() {
        return ResponseEntity.ok(emprestimoService.listarEmprestimosEmAtraso());
    }

    @Operation(summary = "Registrar devolução")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro devolvido"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Id inválido"),
        @ApiResponse(responseCode = "409", description = "Livro já devolvido")
    })
    @PatchMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoResponseDto> registrarDevolucao(
        @PathVariable Long id
    ) {

        return ResponseEntity
                .ok(emprestimoService.registrarDevolucao(id));
    }
}
