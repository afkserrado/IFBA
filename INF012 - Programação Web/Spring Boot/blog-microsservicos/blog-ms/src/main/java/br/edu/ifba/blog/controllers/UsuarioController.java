package br.edu.ifba.blog.controllers;

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

import br.edu.ifba.blog.dtos.UsuarioDto;
import br.edu.ifba.blog.model.Usuario;
import br.edu.ifba.blog.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioRepository repository;
	
	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<UsuarioDto> listar() {
		return UsuarioDto.converte(repository.findAll());
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioDto usuarioDto,
		UriComponentsBuilder uriBuilder) {

		Usuario usuario = usuarioDto.converte();
		repository.save(usuario);

		URI uri = uriBuilder
					.path("/usuarios/{id}")
					.buildAndExpand(usuario.getId())
					.toUri();

		return ResponseEntity
					.created(uri)
					.body(new UsuarioDto(usuario));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(
		@PathVariable Long id,
		@RequestBody @Valid UsuarioDto usuarioDto
	) {

		Usuario usuario = usuarioDto.atualizar(repository, id);
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(
		@PathVariable Long id
	) {

		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
