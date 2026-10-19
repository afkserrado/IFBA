package br.edu.ifba.emprestimos_ms.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Interceptador responsável por propagar o JWT recebido na requisição original
 * para as chamadas Feign realizadas pelo emprestimos-ms.
 *
 * Fluxo de funcionamento:
 *
 * 1. Um cliente envia uma requisição ao emprestimos-ms contendo:
 *    Authorization: Bearer <token>
 *
 * 2. O Spring Security valida esse token antes de liberar o endpoint.
 *
 * 3. Durante o processamento, o emprestimos-ms pode chamar o acervo-ms ou o
 *    usuarios-ms por meio de interfaces Feign.
 *
 * 4. Antes de o Feign enviar uma dessas chamadas, ele invoca automaticamente
 *    o método apply(RequestTemplate template).
 *
 * 5. O RequestTemplate representa a requisição HTTP que o Feign está prestes
 *    a enviar ao outro microsserviço.
 *
 * 6. Este interceptador lê o cabeçalho Authorization da requisição original
 *    e adiciona o mesmo cabeçalho ao RequestTemplate.
 *
 * 7. O Feign utiliza o RequestTemplate modificado para montar e enviar a
 *    requisição HTTP final ao microsserviço de destino.
 *
 * Sem essa propagação, o acervo-ms e o usuarios-ms receberiam chamadas sem
 * token e retornariam 401 Unauthorized.
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

        // Obtém os atributos da requisição HTTP que está sendo processada
        // no momento pela thread atual.
        //
        // O RequestContextHolder permite acessar informações da requisição
        // original, como cabeçalhos, parâmetros e atributos da requisição.
        ServletRequestAttributes atributos =
            (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // Se não houver uma requisição HTTP associada à execução atual,
        // não existe token de cliente para propagar.
        //
        // Isso pode ocorrer, por exemplo, em processamentos agendados,
        // consumidores de mensagens ou chamadas internas fora do contexto
        // de uma requisição HTTP.
        if (atributos == null) {
            return;
        }

        // Obtém a requisição HTTP original e lê o cabeçalho Authorization.
        //
        // O valor esperado é:
        // Authorization: Bearer <token>
        String authorization = atributos.getRequest().getHeader("Authorization");

        // Adiciona o mesmo cabeçalho Authorization à requisição Feign.
        //
        // O RequestTemplate representa a requisição que o Feign enviará ao
        // microsserviço de destino. Ao modificar esse objeto, o cabeçalho
        // será incluído na requisição HTTP final.
        //
        // Dessa forma, o acervo-ms ou o usuarios-ms receberá:
        // Authorization: Bearer <token>
        //
        // e poderá validar novamente a identidade e o papel do usuário
        // que iniciou a operação.
        if (authorization != null && !authorization.isBlank()) {
            template.header("Authorization", authorization);
        }
    }
}