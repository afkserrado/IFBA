package br.edu.ifba.usuarios_ms.messaging;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import br.edu.ifba.usuarios_ms.config.RabbitMQConfig;
import br.edu.ifba.usuarios_ms.dto.UsuarioCriadoEvent;
import br.edu.ifba.usuarios_ms.dto.UsuarioDeletadoEvent;
import br.edu.ifba.usuarios_ms.enums.Role;

@Component
public class UsuarioProducer {

    private final RabbitTemplate rabbitTemplate;

    public UsuarioProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void dispararUsuarioCriado(Long id, String nome, String email, Role role) {
        UsuarioCriadoEvent evento = new UsuarioCriadoEvent(id, nome, email, role);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIO_CRIADO, RabbitMQConfig.ROUTING_KEY_CRIADO, evento);
    }

    public void dispararUsuarioDeletado(Long usuarioId) {
        UsuarioDeletadoEvent evento = new UsuarioDeletadoEvent(usuarioId);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_USUARIO_DELETADO, RabbitMQConfig.ROUTING_KEY_DELETADO, evento);
    }
}
