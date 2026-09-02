package br.edu.ifba.notificacoes_ms.dto;

// Evento recebido quando um usuário é cadastrado no RabbitMQ
// Deve ser compatível com o JSON publicado pelo microsserviço produtor
public record UsuarioCriadoEvent(
    Long id,
    String nome,
    String email
) {
}