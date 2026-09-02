package br.edu.ifba.emprestimos_ms.dto;

import jakarta.validation.constraints.NotNull;

public record EmprestimoRequest(
		
	@NotNull(message = "O ID do usuário é obrigatório")
	Long usuarioId,

	@NotNull(message = "O ID do livro é obrigatório")
	Long livroId

){}
