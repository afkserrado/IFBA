package br.edu.ifba.emprestimos_ms.dto;

import java.time.LocalDate;

public record EmprestimoCriadoEvent(
        Long emprestimoId,
        Long usuarioId,
        String nomeUsuario,
        String emailUsuario,
        Long livroId,
        LocalDate dataEmprestimo,
        LocalDate dataPrevistaDevolucao
) {
}