package br.edu.ifba.notificacoes_ms.dto;

// Evento recebido quando um livro é deletado no RabbitMQ
// Deve ser compatível com o JSON publicado pelo microsserviço produtor
public record LivroDeletadoEvent(
    Long id
) {
}