package br.edu.ifba.blog.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.blog.model.Categoria;
import br.edu.ifba.blog.model.Post;
import br.edu.ifba.blog.model.Usuario;
import br.edu.ifba.blog.repositories.PostRepository;
import br.edu.ifba.blog.repositories.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostDto {

	private Long id;

	@NotNull(message = "O título não pode ser nulo.")
	private String titulo;

	@NotBlank(message = "O texto não pode ser vazio.")
	private String texto;

	@NotBlank(message = "O usuário não pode ser vazio.")
	private String usuario;
	private Categoria categoria;
	
	// O JPA/Hibernate instancia o objeto pelo construtor vazio
	public PostDto() {}

	public PostDto(Post post) {
		this.id = post.getId();
		this.titulo = post.getTitulo();
		this.texto = post.getTexto();
		this.usuario = post.getUsuario().getNome();
		this.categoria = post.getCategoria();
	}
	
	public static List<PostDto> converte(List<Post> lista){
		return lista.stream().map(PostDto::new).collect(Collectors.toList());
	}

	public Post converte(UsuarioRepository userRepository){
		Usuario user = userRepository.findByNome(usuario);
		return new Post(titulo, texto, user, categoria);
	}

	public Post atualizar(PostRepository repository, UsuarioRepository userRepository, Long id) {
		Post post = repository.getReferenceById(id);
		Usuario user = userRepository.findByNome(usuario);

		post.setTitulo(titulo);
		post.setTexto(texto);
		post.setUsuario(user);
		post.setCategoria(categoria);

		return post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
