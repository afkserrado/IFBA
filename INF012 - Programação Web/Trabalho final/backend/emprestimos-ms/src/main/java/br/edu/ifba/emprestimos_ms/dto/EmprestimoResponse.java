package br.edu.ifba.emprestimos_ms.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.edu.ifba.emprestimos_ms.entity.StatusEmprestimo;

public record EmprestimoResponse(
		Long id,
	    Long usuarioId,
	    Long livroId,
	    LocalDate dataEmprestimo,
	    LocalDate dataPrevistaDevolucao,
	    LocalDate dataDevolucao,
	    StatusEmprestimo status,
	    BigDecimal valorMulta,
	    Boolean multaPaga,
	    LocalDateTime dataCriacao,
	    LocalDateTime dataAtualizacao	
) {}
