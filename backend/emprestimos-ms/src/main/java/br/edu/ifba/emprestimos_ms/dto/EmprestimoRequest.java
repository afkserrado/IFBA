package br.edu.ifba.emprestimos_ms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record EmprestimoRequest(
		
	@NotNull(message = "O ID do usuário é obrigatório")
	Long usuarioId,

	@NotNull(message = "O ID do livro é obrigatório")
	Long livroId,
	@NotNull(message = "A data prevista de devolução é obrigatória")
	@FutureOrPresent(message = "A data prevista de devolução deve ser hoje ou no futuro")
	LocalDate dataPrevistaDevolucao
){}
