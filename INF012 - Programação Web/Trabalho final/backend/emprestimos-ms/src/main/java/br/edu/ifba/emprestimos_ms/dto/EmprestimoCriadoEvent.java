package br.edu.ifba.emprestimos_ms.dto;

import java.time.LocalDateTime;

public record EmprestimoCriadoEvent(
        Long emprestimoId,
        Long usuarioId,
        String nomeUsuario,
        String emailUsuario,
        Long livroId,
        LocalDateTime dataEmprestimo,
        LocalDateTime dataPrevistaDevolucao
) {
}