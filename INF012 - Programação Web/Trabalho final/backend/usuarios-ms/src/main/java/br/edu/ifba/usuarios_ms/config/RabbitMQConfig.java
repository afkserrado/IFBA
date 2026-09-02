package br.edu.ifba.usuarios_ms.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Exchange é como se fosse uma agênciad de troca de mensagens. 
    // Neste caso, estamos abrindo duas agências, uma para informar criação e outra para exclusão
    public static final String EXCHANGE_USUARIO_CRIADO = "usuario.criado.exchange";
    public static final String EXCHANGE_USUARIO_DELETADO = "usuario.deletado.exchange";

    
    public static final String ROUTING_KEY_CRIADO = "usuario.evento.criado";
    public static final String ROUTING_KEY_DELETADO = "usuario.evento.deletado";


    /*
    TopicExchange é um tipo de agência flexível do RabbitMQ. 
    Ela permite enviar mensagens usando padrões de texto 
    (como palavras separadas por pontos), 
    facilitando que outros microsserviços filtrem o que querem receber no futuro.
    */
    @Bean
    public TopicExchange exchangeUsuarioCriado() {
        return new TopicExchange(EXCHANGE_USUARIO_CRIADO);
    }

    @Bean
    public TopicExchange exchangeUsuarioDeletado() {
        return new TopicExchange(EXCHANGE_USUARIO_DELETADO);
    }

    // Configura o Spring para transformar automaticamente os objetos Java (Records) em JSON
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
