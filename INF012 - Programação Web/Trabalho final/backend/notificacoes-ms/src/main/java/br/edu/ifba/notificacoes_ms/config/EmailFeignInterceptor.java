// src/main/java/br/edu/ifba/notificacoes_ms/config/EmailFeignInterceptor.java
package br.edu.ifba.notificacoes_ms.config;

import org.springframework.stereotype.Component;
import br.edu.ifba.notificacoes_ms.service.ServiceTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/*
 * Interceptor Feign responsável por adicionar o JWT
 * às requisições enviadas pelo notificacoes-ms ao email-ms.
 *
 * O email-ms é um Resource Server: ele valida o token recebido
 * no cabeçalho Authorization antes de autorizar o envio do e-mail.
 *
 * O notificacoes-ms apenas obtém o token no usuarios-ms
 * e o apresenta nesta requisição.
 */
@Component
public class EmailFeignInterceptor implements RequestInterceptor {

    // Serviço que obtém, armazena e invalida o JWT do notificacoes-ms
    private final ServiceTokenService serviceTokenService;

    /*
     * Construtor com injeção de dependência.
     *
     * O Spring entrega automaticamente uma instância
     * de ServiceTokenService.
     */
    public EmailFeignInterceptor(ServiceTokenService serviceTokenService) {
        this.serviceTokenService = serviceTokenService;
    }

    /*
     * Executado pelo Feign antes de cada requisição.
     *
     * Adiciona o cabeçalho:
     * Authorization: Bearer <token>
     *
     * O token é obtido pelo ServiceTokenService.
     * Se ainda não existir, o serviço realiza login no usuarios-ms.
     */
    @Override
    public void apply(RequestTemplate template) {

        template.header(
                "Authorization",
                "Bearer " + serviceTokenService.getToken()
        );
    }
}