package br.edu.ifba.emprestimos_ms.dto;

import java.time.LocalDateTime;

// DTO para respostas de erro genéricas da API
public record ErroResponseDTO(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    String mensagem,
    String caminho
) {}