package br.edu.ifba.usuarios_ms.dto;

// Evento publicado quando um usuário é deletado no RabbitMQ
public record UsuarioDeletadoEvent(Long usuarioId) {}