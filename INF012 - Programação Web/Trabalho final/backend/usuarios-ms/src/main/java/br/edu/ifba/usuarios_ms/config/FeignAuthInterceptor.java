package br.edu.ifba.usuarios_ms.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * Propaga o token JWT recebido na requisição original
 * para as chamadas Feign realizadas pelo usuarios-ms.
 *
 * Sem isso, o emprestimos-ms recebe uma chamada sem
 * Authorization e responde 401 Unauthorized.
 */
@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    /**
     * Copia o cabeçalho Authorization da requisição original
     * para a requisição Feign que será enviada a outro microsserviço.
     *
     * O método retorna void porque a alteração ocorre diretamente no objeto
     * RequestTemplate recebido como parâmetro. O Feign mantém a referência
     * desse objeto e utiliza a versão modificada ao montar a requisição HTTP.
     */
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