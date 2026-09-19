package br.edu.ifba.emprestimos_ms.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Componente do RabbitMQ que recebe uma mensagem enviada pelo produtor
    // e decide para qual ou quais filas ela será encaminhada
    public static final String EXCHANGE_EMPRESTIMO = "emprestimo.exchange";

    // Chave utilizada pela exchange para decidir o destino (fila)
    // de uma mensagem
    public static final String ROUTING_KEY_EMPRESTIMO_CRIADO =
            "emprestimo.evento.criado";

    public static final String ROUTING_KEY_EMPRESTIMO_DEVOLVIDO =
            "emprestimo.evento.devolvido";

    // Um tipo de exchange que permite encaminhar mensagens conforme
    // padrões de texto nas routing keys
    @Bean
    public TopicExchange emprestimoExchange() {
        return new TopicExchange(EXCHANGE_EMPRESTIMO);
    }

    // Converte objetos java em JSON antes de enviá-los ao RabbitMQ
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}