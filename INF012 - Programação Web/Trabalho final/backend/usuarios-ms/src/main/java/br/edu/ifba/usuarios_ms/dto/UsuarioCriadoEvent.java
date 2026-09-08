package br.edu.ifba.usuarios_ms.dto;

import br.edu.ifba.usuarios_ms.enums.Role;

// Evento publicado quando um usuário é cadastrado no RabbitMQ
public record UsuarioCriadoEvent(
    Long id,
    String nome,
    String email,
    Role role
) {}