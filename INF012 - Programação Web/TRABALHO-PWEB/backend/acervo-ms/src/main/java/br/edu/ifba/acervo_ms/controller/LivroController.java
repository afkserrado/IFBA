package br.edu.ifba.acervo_ms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.acervo_ms.dto.DisponibilidadeResponse;
import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.service.LivroService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1/livros")
@Tag(name = "Livros", description = "Endpoints para gerenciamento do acervo de livros")
public class LivroController {

        private final LivroService livroService;

        public LivroController(LivroService livroService) {
                this.livroService = livroService;
        }

        @PostMapping
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Cadastra um novo livro", description = "Adiciona um livro ao acervo com validação de dados.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso", content = @Content(schema = @Schema(implementation = LivroResponseDTO.class))),
                        @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content)
        })
        public ResponseEntity<LivroResponseDTO> cadastrarLivro(
                        @RequestBody @Valid LivroRequestDTO dto) {
                LivroResponseDTO response = livroService.cadastrarLivro(dto);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        @GetMapping
        @Operation(summary = "Lista os livros de forma paginada", description = "Retorna uma lista resumida de livros cadastrados com paginação flexível.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content)
        })
        public ResponseEntity<Page<LivroResumoResponseDTO>> listarLivrosPaginado(
                        @Parameter(description = "Critério opcional de ordenação") @RequestParam(required = false) String ordenacao,
                        @Parameter(hidden = true) @PageableDefault(size = 10) Pageable pageable) {
                return ResponseEntity.ok(livroService.buscarLivros(ordenacao, pageable));
        }

        @GetMapping("/isbn/{isbn}")
        @Operation(summary = "Busca um livro pelo código ISBN", description = "Busca os detalhes resumidos de um livro através do seu identificador ISBN único.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Livro localizado com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
        })
        public ResponseEntity<LivroResumoResponseDTO> buscarPorIsbn(
                        @Parameter(description = "Código ISBN do livro desejado") @PathVariable String isbn) {
                return ResponseEntity.ok(livroService.buscarLivroPorIsbn(isbn));
        }

        @GetMapping("/titulo")
        @Operation(summary = "Busca livros por correspondência de título", description = "Retorna livros de forma paginada que coincidam parcial ou totalmente com o título informado.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content)
        })
        public ResponseEntity<Page<LivroResumoResponseDTO>> buscarPorTitulo(
                        @Parameter(description = "Texto para pesquisa no título do livro") @RequestParam String titulo,
                        @Parameter(description = "Critério opcional de ordenação") @RequestParam(required = false) String ordenacao,
                        @Parameter(hidden = true) @PageableDefault(size = 10) Pageable pageable) {
                return ResponseEntity.ok(livroService.buscarLivrosPorTitulo(titulo, ordenacao, pageable));
        }

        @GetMapping("/autor")
        @Operation(summary = "Busca livros pelo nome do autor", description = "Retorna livros de forma paginada associados ao autor pesquisado.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content)
        })
        public ResponseEntity<Page<LivroResumoResponseDTO>> buscarPorAutor(
                        @Parameter(description = "Nome do autor para filtro") @RequestParam String autor,
                        @Parameter(description = "Critério opcional de ordenação") @RequestParam(required = false) String ordenacao,
                        @Parameter(hidden = true) @PageableDefault(size = 10) Pageable pageable) {
                return ResponseEntity.ok(livroService.buscarLivrosPorAutor(autor, ordenacao, pageable));
        }

        @PutMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Atualiza os dados de um livro existente", description = "Modifica completamente os dados de um livro a partir do seu ID.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não localizado para atualização", content = @Content)
        })
        public ResponseEntity<LivroResumoResponseDTO> atualizarLivro(
                        @Parameter(description = "Identificador numérico do livro") @PathVariable Long id,
                        @RequestBody @Valid LivroRequestDTO dto) {
                return ResponseEntity.ok(livroService.atualizarLivro(id, dto));
        }

        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Remove um livro do acervo", description = "Deleta o registro de um livro de forma permanente utilizando o ID informando.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Livro removido com sucesso"),
                        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não encontrado para exclusão", content = @Content)
        })
        public ResponseEntity<Void> removerLivro(
                        @Parameter(description = "Identificador numérico do livro") @PathVariable Long id) {
                livroService.removerLivro(id);
                return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}/disponibilidade")
        @Operation(summary = "Verifica se um livro possui estoque disponível", description = "Retorna uma flag booleana indicando se existem unidades físicas do livro em estoque.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Consulta realizada"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não localizado", content = @Content)
        })
        public ResponseEntity<DisponibilidadeResponse> verificarDisponibilidade(
                        @Parameter(description = "Identificador numérico do livro") @PathVariable Long id) {

                boolean disponivel = livroService.estaDisponivel(id);

                return ResponseEntity.ok(
                                new DisponibilidadeResponse(disponivel));
        }

        @PostMapping("/{id}/reduzir-estoque")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Subtrai uma unidade do estoque do livro", description = "Decrementa o estoque do livro indicado em uma unidade (usado em novos empréstimos).")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Estoque decrementado com sucesso"),
                        @ApiResponse(responseCode = "400", description = "Estoque insuficiente", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não localizado", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content)
        })
        public ResponseEntity<Void> reduzirEstoque(
                        @Parameter(description = "Identificador numérico do livro") @PathVariable Long id) {
                livroService.reduzirEstoque(id);
                return ResponseEntity.ok().build();
        }

        @PostMapping("/{id}/aumentar-estoque")
        @PreAuthorize("hasRole('ADMIN')")
        @Operation(summary = "Adiciona uma unidade ao estoque do livro", description = "Incrementa o estoque do livro indicado em uma unidade (usado na devolução de empréstimos).")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Estoque incrementado com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Livro não localizado", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content)
        })
        public ResponseEntity<Void> aumentarEstoque(
                        @Parameter(description = "Identificador numérico do livro") @PathVariable Long id) {
                livroService.aumentarEstoque(id);
                return ResponseEntity.ok().build();
        }
}
