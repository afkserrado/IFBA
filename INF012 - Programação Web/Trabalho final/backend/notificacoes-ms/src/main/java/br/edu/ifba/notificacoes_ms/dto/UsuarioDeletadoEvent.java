package br.edu.ifba.notificacoes_ms.dto;

// Evento recebido quando um usuário é deletado no RabbitMQ
// Deve ser compatível com o JSON publicado pelo microsserviço produtor
public record UsuarioDeletadoEvent(Long usuarioId) {}
