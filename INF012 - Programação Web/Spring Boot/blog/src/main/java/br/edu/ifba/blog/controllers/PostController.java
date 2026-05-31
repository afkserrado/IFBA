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

import br.edu.ifba.blog.dtos.PostDto;
import br.edu.ifba.blog.model.Post;
import br.edu.ifba.blog.repositories.PostRepository;
import br.edu.ifba.blog.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    
	private PostRepository repository;
	private UsuarioRepository userRepository;
	
	public PostController(PostRepository repository, UsuarioRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}
	
	@GetMapping
    public List<PostDto> listarTudo() {
    	return PostDto.converte(repository.findAll());
    }

	// @GetMapping
	// public List<PostDto> listarPorTitulo(@RequestParam String titulo) {
	// 	return PostDto.converte(repository.findByTitulo(titulo));
	// }

	// @GetMapping
	// public List<PostDto> listarPorTitulo(String titulo) {
	// 	return PostDto.converte(repository.searchByTitleLike(titulo));
	// }

	// @GetMapping
	// public List<PostDto> listaPorUsuario(String usuario) {
	// 	return PostDto.converte(repository.findByUsuarioNome(usuario));
	// }

	@PostMapping
	public ResponseEntity<PostDto> cadastrar(@RequestBody @Valid PostDto postDto, UriComponentsBuilder uriBuilder) {
		Post post = postDto.converte(userRepository);
		repository.save(post);

		URI uri = uriBuilder
					.path("/posts/{id}")
					.buildAndExpand(post.getId())
					.toUri();
		
		return ResponseEntity
				.created(uri)
				.body(new PostDto(post));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PostDto> atualizar(
		@PathVariable Long id,
		@RequestBody @Valid PostDto postDto
	) {
		Post post = postDto.atualizar(repository, userRepository, id);
		return ResponseEntity.ok(new PostDto(post));		
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
