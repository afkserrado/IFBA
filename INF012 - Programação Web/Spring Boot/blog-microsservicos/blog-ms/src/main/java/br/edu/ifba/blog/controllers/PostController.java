package br.edu.ifba.blog.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import br.edu.ifba.blog.clients.EmailClient;
import br.edu.ifba.blog.dtos.EmailDto;
import br.edu.ifba.blog.dtos.PostDto;
import br.edu.ifba.blog.model.Post;
import br.edu.ifba.blog.repositories.PostRepository;
import br.edu.ifba.blog.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    
	private PostRepository repository;
	private UsuarioRepository userRepository;
	
	private EmailClient emailClient;
	
	public PostController(PostRepository repository, UsuarioRepository userRepository, EmailClient emailClient) {
		this.repository = repository;
		this.userRepository = userRepository;
		this.emailClient = emailClient;
	}
	
	@GetMapping
    public List<PostDto> listarTudo() {

		emailClient.sendEmail(
			new EmailDto(
				"akserrado@gmail.com",
				"akserrado@gmail.com",
				"Listar tudo",
				"Listando todos os posts do banco de dados."
			)
		);

    	return PostDto.converte(repository.findAll());
    }

	// @GetMapping
	public List<PostDto> listarPorTitulo(@RequestParam String titulo) {
		return PostDto.converte(repository.findByTitulo(titulo));
	}

	// Com paginação, utilizando PageRequest
	@Operation(
		summary = "Listar posts",
		description = "Retorna posts por título ou todos os posts"
	)
	@ApiResponse(
		responseCode = "200",
		description = "Lista de posts"
	)
	//@GetMapping
	public Page<PostDto> listarPorTitulo(
		@RequestParam(required = false) String titulo,
		@RequestParam int pagina,
		@RequestParam int qtd
	) {
		
		// Cria o objeto de paginação
		Pageable pageable = PageRequest.of(pagina, qtd);

		if(titulo != null && !titulo.equals("")) {

			return PostDto.converte(
				repository.findByTitulo(titulo, pageable)
			);
		}

		return PostDto.converte(repository.findAll(pageable));
	}

	// @GetMapping
	// public List<PostDto> listarPorTitulo(String titulo) {
	// 	return PostDto.converte(repository.searchByTitleLike(titulo));
	// }

	// @GetMapping
	// public List<PostDto> listaPorUsuario(String usuario) {
	// 	return PostDto.converte(repository.findByUsuarioNome(usuario));
	// }

	@PostMapping
	@Transactional // Redundante
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

	// @DeleteMapping("/{id}")
	// @Transactional
	// public ResponseEntity<?> deletar(
	// 	@PathVariable Long id
	// ) {

	// 	repository.deleteById(id);
	// 	return ResponseEntity.ok().build();
	// }

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(
		@PathVariable Long id
	) {

		Post post = repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

		repository.delete(post);
		return ResponseEntity.noContent().build();
	}
}
