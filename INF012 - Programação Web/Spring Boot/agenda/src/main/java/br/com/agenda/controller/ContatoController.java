package br.com.agenda.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.agenda.dto.ContatoDto;
import br.com.agenda.dto.ContatoResponseDto;
import br.com.agenda.service.ContatoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    private ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    @Transactional // Redundante
    public ResponseEntity<ContatoDto> criarContato(
        @RequestBody @Valid ContatoDto dto, UriComponentsBuilder uriBuilder
    ) {
        
        ContatoDto contatoSalvo = contatoService.criarContato(dto);

        URI uri = uriBuilder
                    .path("/contatos/{id}")
                    .buildAndExpand(contatoSalvo.getId())
                    .toUri();

        return ResponseEntity
                .created(uri)
                .body(contatoSalvo);
    }

    @GetMapping
    public List<ContatoResponseDto> listarContatos(
        @RequestParam (required = false) String sort 
    ) {
        return contatoService.listarContatos(sort);
    }

    @GetMapping(params = {"page", "size"})
    public Page<ContatoResponseDto> listarContatos(
        Pageable pageable,
        @RequestParam (required = false) String sort
    ) {
        return contatoService.listarContatos(pageable, sort);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDto> buscarContatoPorId(
        @PathVariable Long id
    ) {
        
        ContatoResponseDto contatoEncontrado = contatoService.buscarContatoPorId(id);
        return ResponseEntity
                .ok(contatoEncontrado);
    }

    @GetMapping("/busca")
    // @GetMapping(value = "/busca", params = "nome")
    public ResponseEntity<List<ContatoResponseDto>> buscarContatoPorNome(
        @RequestParam String nome
    ) {

        List<ContatoResponseDto> contatosEncontrados = contatoService.buscarContatoPorNome(nome);
        return ResponseEntity.ok(contatosEncontrados);
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<ContatoResponseDto>> buscarContatosFavoritos() {
        List<ContatoResponseDto> contatosEncontrados = contatoService.buscarContatoFavoritos();
        return ResponseEntity.ok(contatosEncontrados);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ContatoResponseDto> atualizarContato(
        @PathVariable Long id,
        @RequestBody @Valid ContatoDto dto
    ) {

        ContatoResponseDto contatoAtualizado = contatoService.atualizarContato(id, dto);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> apagarContato(
        @PathVariable Long id
    ) {

        contatoService.apagarContato(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
