package br.edu.ifba.notificacoes_ms.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.edu.ifba.notificacoes_ms.config.RabbitMQConfig;
import br.edu.ifba.notificacoes_ms.dto.LivroCadastradoEvent;
import br.edu.ifba.notificacoes_ms.dto.LivroDeletadoEvent;
import br.edu.ifba.notificacoes_ms.dto.UsuarioCriadoEvent;
import br.edu.ifba.notificacoes_ms.dto.UsuarioDeletadoEvent;
import br.edu.ifba.notificacoes_ms.service.NotificacaoService;

// Bean gerenciado pelo container Spring
// Spring cria automaticamente uma instância na inicialização da aplicação
@Component
public class NotificacaoConsumer {

    private final NotificacaoService notificacaoService;

    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    // Monitora as filas do RabbitMQ
    // Quando uma mensagem chegar nelas,
    // o respectivo método é executado

    @RabbitListener(queues = RabbitMQConfig.FILA_LIVRO_CADASTRADO)
    public void consumirLivroCadastrado(LivroCadastradoEvent event) {
        notificacaoService.notificarLivroCadastrado(event);
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_LIVRO_DELETADO)
    public void consumirLivroDeletado(LivroDeletadoEvent event) {
        notificacaoService.notificarLivroDeletado(event);
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_USUARIO_CRIADO)
    public void consumirUsuarioCriado(UsuarioCriadoEvent event) {
        notificacaoService.notificarUsuarioCriado(event);
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_USUARIO_DELETADO)
    public void consumirUsuarioDeletado(UsuarioDeletadoEvent event) {
        notificacaoService.notificarUsuarioDeletado(event);
    }
}