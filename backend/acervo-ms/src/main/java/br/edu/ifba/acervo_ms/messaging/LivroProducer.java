package br.edu.ifba.acervo_ms.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.edu.ifba.acervo_ms.config.RabbitMQConfig;
import br.edu.ifba.acervo_ms.dto.LivroCadastradoEvent;
import br.edu.ifba.acervo_ms.dto.LivroDeletadoEvent;

@Service
public class LivroProducer {
    
    // Permite interagir com o RabbitMQ para enviar e receber mensagens entre outras coisas
    private final RabbitTemplate rabbitTemplate;

    public LivroProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Converte o objeto Java em JSON
    // Envia a mensagem para o Exchange, junto com uma Routing Key
    // O Exchange, com a Routing Key, encaminha a mensagem para as filas compatíveis
    public void publicarLivroCadastrado(LivroCadastradoEvent event) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_LIVRO,
            RabbitMQConfig.ROUTING_KEY_LIVRO_CADASTRADO,
            event
        );
    }

    public void publicarLivroDeletado(LivroDeletadoEvent event) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_LIVRO,
            RabbitMQConfig.ROUTING_KEY_LIVRO_DELETADO,
            event
        );
    }
}
