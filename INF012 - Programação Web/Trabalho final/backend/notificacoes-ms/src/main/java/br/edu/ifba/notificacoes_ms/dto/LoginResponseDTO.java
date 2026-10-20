// src/main/java/br/edu/ifba/notificacoes_ms/dto/LoginResponseDTO.java
package br.edu.ifba.notificacoes_ms.dto;

public record LoginResponseDTO(
        String token,
        String tipo
) {}