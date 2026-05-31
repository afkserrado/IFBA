package br.edu.ifba.blog.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blog.dtos.UsuarioDto;
import br.edu.ifba.blog.repositories.UsuarioRepository;

@RestController
public class UsuarioController {

	private UsuarioRepository repository;
	
	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@RequestMapping("/usuarios")
	public List<UsuarioDto> listar() {
		return UsuarioDto.converte(repository.findAll());
	}
}
