package br.edu.ifba.blog.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.blog.model.Usuario;

public class UsuarioDto {

	private Long id;
    private String nome;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
	}
	
	public static List<UsuarioDto> converte(List<Usuario> lista){
		return lista.stream().map(UsuarioDto::new).collect(Collectors.toList());
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
}
