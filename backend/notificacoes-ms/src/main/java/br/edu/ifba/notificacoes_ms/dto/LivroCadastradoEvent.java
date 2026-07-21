package br.edu.ifba.notificacoes_ms.dto;

// Evento recebido quando um livro é cadastrado no RabbitMQ
// Deve ser compatível com o JSON publicado pelo microsserviço produtor
public record LivroCadastradoEvent(
    Long id,
    String titulo,
    String autor,
    String isbn
) {
}