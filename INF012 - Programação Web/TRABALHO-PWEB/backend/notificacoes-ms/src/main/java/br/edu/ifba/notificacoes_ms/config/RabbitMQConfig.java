package br.edu.ifba.notificacoes_ms.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    // "Contratos" que serão "assinados" pelo microsserviço consumidor
    // Exchanges utilizadas pelos produtores
    public static final String EXCHANGE_LIVRO = "livro.exchange";
    public static final String EXCHANGE_USUARIO_CRIADO = "usuario.criado.exchange";
    public static final String EXCHANGE_USUARIO_DELETADO = "usuario.deletado.exchange";

    // Routing keys utilizadas pelos produtores
    public static final String ROUTING_KEY_LIVRO_CADASTRADO = "livro.evento.cadastrado";
    public static final String ROUTING_KEY_LIVRO_DELETADO = "livro.evento.deletado";
    public static final String ROUTING_KEY_USUARIO_CRIADO = "usuario.evento.criado";
    public static final String ROUTING_KEY_USUARIO_DELETADO = "usuario.evento.deletado";

    // Filas do microsserviço consumidor
    public static final String FILA_LIVRO_CADASTRADO = "notificacoes.livro.cadastrado.queue";
    public static final String FILA_LIVRO_DELETADO = "notificacoes.livro.deletado.queue";
    public static final String FILA_USUARIO_CRIADO = "notificacoes.usuario.criado.queue";
    public static final String FILA_USUARIO_DELETADO = "notificacoes.usuario.deletado.queue";

    // Exchanges declaradas pelos produtores
    // Se já foram criadas pelos produtores, o consumidor passa a referenciá-las
    // Necessário para conseguir bindar as filas do consumidor com as exchanges
    // dos produtores
    @Bean
    public TopicExchange livroExchange() {
        return new TopicExchange(EXCHANGE_LIVRO);
    }

    @Bean
    public TopicExchange usuarioCriadoExchange() {
        return new TopicExchange(EXCHANGE_USUARIO_CRIADO);
    }

    @Bean
    public TopicExchange usuarioDeletadoExchange() {
        return new TopicExchange(EXCHANGE_USUARIO_DELETADO);
    }

    // Criam as filas nas quais as mensagens enviadas pelos produtores
    // serão roteadas pela exchange e, se houver binding compatível, serão entregues à fila
    @Bean
    public Queue filaLivroCadastrado() {
        return new Queue(FILA_LIVRO_CADASTRADO, true);
    }

    @Bean
    public Queue filaLivroDeletado() {
        return new Queue(FILA_LIVRO_DELETADO, true);
    }

    @Bean
    public Queue filaUsuarioCriado() {
        return new Queue(FILA_USUARIO_CRIADO, true);
    }

    @Bean
    public Queue filaUsuarioDeletado() {
        return new Queue(FILA_USUARIO_DELETADO, true);
    }

    // Fazem a ligação entre as filas e os exchanges usando uma
    // routing key específica
    @Bean
    public Binding bindingLivroCadastrado() {
        return BindingBuilder
                .bind(filaLivroCadastrado())
                .to(livroExchange())
                .with(ROUTING_KEY_LIVRO_CADASTRADO);
    }

    @Bean
    public Binding bindingLivroDeletado() {
        return BindingBuilder
                .bind(filaLivroDeletado())
                .to(livroExchange())
                .with(ROUTING_KEY_LIVRO_DELETADO);
    }

    @Bean
    public Binding bindingUsuarioCriado() {
        return BindingBuilder
                .bind(filaUsuarioCriado())
                .to(usuarioCriadoExchange())
                .with(ROUTING_KEY_USUARIO_CRIADO);
    }

    @Bean
    public Binding bindingUsuarioDeletado() {
        return BindingBuilder
                .bind(filaUsuarioDeletado())
                .to(usuarioDeletadoExchange())
                .with(ROUTING_KEY_USUARIO_DELETADO);
    }

    // Converte automaticamente objetos Java em JSON
    // e JSON em objetos Java ao consumir mensagens
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
