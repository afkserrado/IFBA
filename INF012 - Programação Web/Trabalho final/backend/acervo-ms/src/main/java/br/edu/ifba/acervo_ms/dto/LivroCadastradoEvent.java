package br.edu.ifba.acervo_ms.dto;

// Evento publicado quando um livro é cadastrado no RabbitMQ
public record LivroCadastradoEvent(
    Long id,
    String titulo,
    String autor,
    String isbn
) {}