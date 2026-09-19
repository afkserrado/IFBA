package br.edu.ifba.notificacoes_ms.dto;

import java.time.LocalDate;

// Evento recebido quando um empréstimo é devolvido no RabbitMQ
// Deve ser compatível com o JSON publicado pelo microsserviço produtor
public record EmprestimoDevolvidoEvent(
    Long emprestimoId,
    Long usuarioId,
    String nomeUsuario,
    String emailUsuario,
    Long livroId,
    LocalDate dataDevolucao
) {
}