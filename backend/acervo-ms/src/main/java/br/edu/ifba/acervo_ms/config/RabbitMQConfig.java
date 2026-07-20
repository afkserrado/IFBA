package br.edu.ifba.acervo_ms.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Informa ao Spring que a classe contém métodos @Bean
public class RabbitMQConfig {

    // A Exchange funciona como uma agência de correios,
    // recebendo mensagens do produtor e decidindo para quais
    // filas elas serão encaminhadas
    public static final String EXCHANGE_LIVRO = "livro.exchange";

    // Routing Key é a chave de roteamento usada pela Exchange para decidir
    // para quais filas encaminhar a mensagem
    public static final String ROUTING_KEY_LIVRO_CADASTRADO = "livro.evento.cadastrado";
    public static final String ROUTING_KEY_LIVRO_DELETADO = "livro.evento.deletado";
    
    /*
    TopicExchange é um tipo de Exchange que permite padrões como:
        livro.*
        livro.evento.#

    O caractere * substitui exatamente uma palavra.
    O caractere # substitui zero ou mais palavras.

    Assim, uma fila vinculada com livro.evento.# pode receber eventos de:
        livro.evento.cadastrado
        livro.evento.deletado
        livro.evento.atualizado
    */
    @Bean
    public TopicExchange exchangeLivroCadastrado() {
        return new TopicExchange(EXCHANGE_LIVRO);
    }

    @Bean
    public TopicExchange exchangeLivroDeletado() {
        return new TopicExchange(EXCHANGE_LIVRO);
    }

    // Converte automaticamente objetos Java em JSON
    // e JSON em objetos Java ao consumir mensagens.
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}