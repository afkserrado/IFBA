package br.edu.ifba.usuarios_ms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// DTO para respostas de erro de validação de campos (400 Bad Request)
public record ErroValidationDTO(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    Map<String, List<String>> campos
) {}