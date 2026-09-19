package br.edu.ifba.emprestimos_ms.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.edu.ifba.emprestimos_ms.dto.EmprestimoRequestDTO;
import br.edu.ifba.emprestimos_ms.dto.EmprestimoResponseDTO;
import br.edu.ifba.emprestimos_ms.service.EmprestimoService;

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
    @Operation(summary = "Registra um novo empréstimo", description = "Cria um registro de empréstimo validando a disponibilidade do livro e situação do usuário.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empréstimo registrado com sucesso", content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Livro não encontrado no serviço de acervo", content = @Content),
        @ApiResponse(responseCode = "409", description = "Conflito de regra de negócio (ex.: multa pendente, usuário inválido, livro indisponível)", content = @Content),
        @ApiResponse(responseCode = "503", description = "Serviço de usuários ou acervo indisponível", content = @Content)
    })
    public ResponseEntity<EmprestimoResponseDTO> cadastrarEmprestimo(@Valid @RequestBody EmprestimoRequestDTO request) {
        EmprestimoResponseDTO response = emprestimoService.cadastrarEmprestimo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/devolucao")
    @Operation(summary = "Registra a devolução de um livro", description = "Finaliza um empréstimo ativo mudando seu status e atualizando o estoque do acervo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Devolução registrada com sucesso", content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Identificador de empréstimo não encontrado", content = @Content),
        @ApiResponse(responseCode = "409", description = "Empréstimo já foi devolvido anteriormente ou outra regra de negócio impedindo a operação", content = @Content),
        @ApiResponse(responseCode = "503", description = "Serviço de acervo indisponível", content = @Content)
    })
    public ResponseEntity<EmprestimoResponseDTO> registrarDevolucao(
        @Parameter(description = "ID do empréstimo a ser encerrado") @PathVariable @NonNull Long id
    ) {
        EmprestimoResponseDTO response = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os empréstimos", description = "Retorna o histórico completo de todos os empréstimos registrados no microsserviço.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista recuperada com sucesso")
    })
    public ResponseEntity<List<EmprestimoResponseDTO>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Consulta empréstimos de um usuário", description = "Retorna todos os empréstimos (ativos e encerrados) vinculados a um usuário específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empréstimos do usuário gerada"),
    })
    public ResponseEntity<List<EmprestimoResponseDTO>> consultarEmprestimosPorUsuario(
        @Parameter(description = "ID do usuário consultado") @PathVariable Long usuarioId
    ) {
        return ResponseEntity.ok(emprestimoService.consultarPorUsuario(usuarioId));
    }

    @PostMapping("/{id}/cancelamento")
    @Operation(summary = "Cancela um empréstimo", description = "Cancela um empréstimo com status ATIVO, devolvendo o exemplar ao acervo e alterando o status para CANCELADO. A data de devolução não é preenchida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empréstimo cancelado com sucesso", content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Empréstimo não encontrado", content = @Content),
        @ApiResponse(responseCode = "409", description = "Empréstimo não está com status ATIVO ou outra regra de negócio impedindo o cancelamento", content = @Content),
        @ApiResponse(responseCode = "503", description = "Serviço de acervo indisponível", content = @Content)
    })
    public ResponseEntity<EmprestimoResponseDTO> cancelarEmprestimo(
        @Parameter(description = "ID do empréstimo a ser cancelado") @PathVariable @NonNull Long id
    ) {
        EmprestimoResponseDTO response = emprestimoService.cancelarEmprestimo(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/pagar-multa")
    @Operation(
        summary = "Registra o pagamento de uma multa",
        description = "Marca uma multa de empréstimo como paga, permitindo que o usuário realize novos empréstimos e possa ser excluído."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Multa paga com sucesso",
            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Empréstimo não encontrado",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Empréstimo não possui multa ou multa já foi paga",
            content = @Content
        )
    })
    public ResponseEntity<EmprestimoResponseDTO> pagarMulta(
        @Parameter(description = "ID do empréstimo com multa a pagar")
        @PathVariable @NonNull Long id
    ) {
        EmprestimoResponseDTO response = emprestimoService.pagarMulta(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/livros/{livroId}/ativos/existe")
    @Operation(summary = "Verifica empréstimo ativo por livro", description = "Consulta rápida para checar se uma unidade do livro informado está atualmente emprestada.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verificação realizada com sucesso")
    })
    public ResponseEntity<Boolean> verificarEmprestimoAtivoPorLivro(
        @Parameter(description = "ID do livro avaliado") @PathVariable Long livroId
    ) {
        boolean existe = emprestimoService.existeEmprestimoAtivoPorLivro(livroId);
        return ResponseEntity.ok(existe);
    }

    // Apenas para testes
    @PutMapping("/{id}/simular-atraso")
    @Operation(
        summary = "Simula atraso na devolução (apenas para testes)",
        description = "Altera a data prevista de devolução de um empréstimo. Use apenas em ambiente de testes para simular multas."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Data alterada com sucesso",
            content = @Content(schema = @Schema(implementation = EmprestimoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Empréstimo não encontrado",
            content = @Content
        )
    })
    public ResponseEntity<EmprestimoResponseDTO> simularAtraso(
            @Parameter(description = "ID do empréstimo")
            @PathVariable @NonNull Long id,
            @Parameter(description = "Nova data prevista de devolução (deve ser uma data passada)")
            @RequestParam @NonNull LocalDateTime dataPrevistaDevolucao) {

        EmprestimoResponseDTO response = emprestimoService.simularAtraso(id, dataPrevistaDevolucao);
        return ResponseEntity.ok(response);
    }
}