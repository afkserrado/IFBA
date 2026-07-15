package br.edu.ifba.acervo_ms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.acervo_ms.dto.LivroRequestDTO;
import br.edu.ifba.acervo_ms.dto.LivroResponseDTO;
import br.edu.ifba.acervo_ms.dto.LivroResumoResponseDTO;
import br.edu.ifba.acervo_ms.service.LivroService;
import jakarta.validation.Valid;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrarLivro(
        @RequestBody @Valid LivroRequestDTO dto
    ) {
        LivroResponseDTO response = livroService.cadastrarLivro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<LivroResumoResponseDTO>> listarLivrosPaginado(
        @RequestParam(required = false) String ordenacao,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(livroService.buscarLivros(ordenacao, pageable));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroResumoResponseDTO> buscarPorIsbn(
        @PathVariable String isbn
    ) {
        return ResponseEntity.ok(livroService.buscarLivroPorIsbn(isbn));
    }

    @GetMapping("/titulo")
    public ResponseEntity<Page<LivroResumoResponseDTO>> buscarPorTitulo(
        @RequestParam String titulo,
        @RequestParam(required = false) String ordenacao,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(livroService.buscarLivrosPorTitulo(titulo, ordenacao, pageable));
    }

    @GetMapping("/autor")
    public ResponseEntity<Page<LivroResumoResponseDTO>> buscarPorAutor(
        @RequestParam String autor,
        @RequestParam(required = false) String ordenacao,
        @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(livroService.buscarLivrosPorAutor(autor, ordenacao, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResumoResponseDTO> atualizarLivro(
        @PathVariable Long id,
        @RequestBody @Valid LivroRequestDTO dto
    ) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/disponibilidade")
    public ResponseEntity<Map<String, Boolean>> verificarDisponibilidade(
        @PathVariable Long id
    ) {
        boolean disponivel = livroService.estaDisponivel(id);
        return ResponseEntity.ok(Map.of("disponivel", disponivel));
    }

    @PatchMapping("/{id}/reduzir-estoque")
    public ResponseEntity<Void> reduzirEstoque(@PathVariable Long id) {
        livroService.reduzirEstoque(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/aumentar-estoque")
    public ResponseEntity<Void> aumentarEstoque(@PathVariable Long id) {
        livroService.aumentarEstoque(id);
        return ResponseEntity.ok().build();
    }
}