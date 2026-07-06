package br.com.biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.biblioteca.dto.LivroRequestDto;
import br.com.biblioteca.dto.LivroResponseDto;
import br.com.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {
    
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @Operation(summary = "Cadastrar livro")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Livro criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<LivroRequestDto> cadastrarLivro(
        @RequestBody @Valid LivroRequestDto dto,
        UriComponentsBuilder uriBuilder
    ) {

        LivroRequestDto livroSalvo = livroService.cadastrarLivro(dto);

        URI uri = uriBuilder
                    .path("/livros/{id}")
                    .buildAndExpand(livroSalvo.getId())
                    .toUri();

        return ResponseEntity
                .created(uri)
                .body(livroSalvo);
    }

    @Operation(summary = "Listar livros")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livros listados")
    })
    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> buscarLivros() {
        return ResponseEntity.ok(livroService.buscarLivros());
    }

    @Operation(summary = "Buscar livro por id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro encontrado"),
        @ApiResponse(responseCode = "404", description = "Id inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarLivroPorId(
        @PathVariable Long id
    ) {

        LivroResponseDto livroEncontrado = livroService.buscarLivroPorId(id);
        return ResponseEntity
                .ok(livroEncontrado);
    }

    @Operation(summary = "Atualizar livro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Livro atualizado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Id inválido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
        @PathVariable Long id,
        @RequestBody @Valid LivroRequestDto dto
    ) {

        LivroResponseDto livroAtualizado = livroService.atualizarLivro(id, dto);
        return ResponseEntity
                .ok(livroAtualizado);
    }

    @Operation(summary = "Remover livro")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Livro removido"),
        @ApiResponse(responseCode = "404", description = "Id inválido"),
        @ApiResponse(responseCode = "409", description = "Livro emprestado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(
        @PathVariable Long id
    ) {

        livroService.removerLivro(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
