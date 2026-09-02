package br.edu.ifba.acervo_ms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ErroValidationDTO(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    Map<String, List<String>> campos
) {}
