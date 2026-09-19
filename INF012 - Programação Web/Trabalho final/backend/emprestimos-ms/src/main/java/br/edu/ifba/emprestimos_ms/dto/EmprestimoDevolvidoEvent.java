package br.edu.ifba.emprestimos_ms.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EmprestimoDevolvidoEvent(
        Long emprestimoId,
        Long usuarioId,
        String nomeUsuario,
        String emailUsuario,
        Long livroId,
        LocalDateTime dataDevolucao,
        BigDecimal valorMulta
) {
}