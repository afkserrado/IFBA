package br.edu.ifba.acervo_ms.dto;

// Evento publicado quando um livro é deletado no RabbitMQ
public record LivroDeletadoEvent(Long id) {}
