package br.edu.ifba.usuarios_ms.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Informa ao Spring que a classe contém métodos @Bean
public class RabbitMQConfig {

    // A Exchange funciona como uma agência de correios,
    // recebendo mensagens do produtor e decidindo para quais
    // filas elas serão encaminhadas
    public static final String EXCHANGE_USUARIO_CRIADO = "usuario.criado.exchange";
    public static final String EXCHANGE_USUARIO_DELETADO = "usuario.deletado.exchange";

    // Routing Key é a chave de roteamento usada pela Exchange para decidir
    // para quais filas encaminhar a mensagem
    public static final String ROUTING_KEY_CRIADO = "usuario.evento.criado";
    public static final String ROUTING_KEY_DELETADO = "usuario.evento.deletado";

    /*
    TopicExchange é um tipo de Exchange que permite padrões como:
        usuario.*
        usuario.evento.#

    O caractere * substitui exatamente uma palavra.
    O caractere # substitui zero ou mais palavras.

    Assim, uma fila vinculada com usuario.evento.# pode receber eventos de:
        usuario.evento.criado
        usuario.evento.deletado
        usuario.evento.atualizado
    */
    @Bean
    public TopicExchange exchangeUsuarioCriado() {
        return new TopicExchange(EXCHANGE_USUARIO_CRIADO);
    }

    @Bean
    public TopicExchange exchangeUsuarioDeletado() {
        return new TopicExchange(EXCHANGE_USUARIO_DELETADO);
    }

    // Converte automaticamente objetos Java em JSON
    // e JSON em objetos Java ao consumir mensagens
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}