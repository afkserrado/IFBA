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
import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {
    
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

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

    @GetMapping
    public ResponseEntity<List<LivroResponseDto>> buscarLivros() {
        return ResponseEntity.ok(livroService.buscarLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> buscarLivroPorId(
        @PathVariable Long id
    ) {

        LivroResponseDto livroEncontrado = livroService.buscarLivroPorId(id);
        return ResponseEntity
                .ok(livroEncontrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> atualizarLivro(
        @PathVariable Long id,
        @RequestBody @Valid LivroRequestDto dto
    ) {

        LivroResponseDto livroAtualizado = livroService.atualizarLivro(id, dto);
        return ResponseEntity
                .ok(livroAtualizado);
    }

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
