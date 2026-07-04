package br.edu.ifba.blog.dtos;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;

import br.edu.ifba.blog.model.Usuario;
import br.edu.ifba.blog.repositories.UsuarioRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioDto {

	private Long id;

	@NotNull(message = "O nome não pode ser nulo.")
    private String nome;

	@NotBlank(message = "O login não pode ser vazio.")
	@Length(min = 5, message = "O login tem menos de 5 caracteres.")
	private String login;

	private String senha;
	
	// O JPA/Hibernate instancia o objeto pelo construtor vazio
	public UsuarioDto() {}

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
	}
	
	public static List<UsuarioDto> converte(List<Usuario> lista){
		return lista.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	public Usuario converte() {
		return new Usuario(id, nome, login, senha);
	}

	public Usuario atualizar(UsuarioRepository userRepository, Long id) {
		Usuario usuario = userRepository.getReferenceById(id);

		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);

		return usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
