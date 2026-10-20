package br.edu.ifba.acervo_ms.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Propaga o token JWT recebido na requisição original
 * para as chamadas Feign realizadas pelo acervo-ms.
 *
 * Sem isso, o emprestimos-ms recebe a chamada de validação
 * de exclusão sem Authorization e responde 401 Unauthorized.
 */
@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        ServletRequestAttributes atributos =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (atributos == null) {
            return;
        }

        String authorization = atributos.getRequest().getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            template.header("Authorization", authorization);
        }
    }
}