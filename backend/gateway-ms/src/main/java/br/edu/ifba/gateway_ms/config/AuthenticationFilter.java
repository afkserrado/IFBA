package main.java.br.edu.ifba.gateway_ms.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@Order(-1) // Força o filtro a rodar no início da execução do Gateway
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${JWT_SECRET:minha-chave-secreta-super-protegida-com-mais-de-trinta-e-dois-bytes}")
    private String secret; 

    public AuthenticationFilter() {
        super(Config.class); // Necessário para a inicialização da classe base
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

        
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Cabeçalho Authorization ausente", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Token malformatado", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.replace("Bearer ", ""); 

            try {
                
                SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                
              
                Claims claims = Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                String email = claims.getSubject();
                String role = claims.get("role", String.class); 

                // Valida as permissões se houver roles exigidas para a rota no application.yml
                if (config.getRolesPermitidas() != null && !config.getRolesPermitidas().isEmpty()) {
                    boolean possuiPermissao = config.getRolesPermitidas().stream()
                            .anyMatch(rolePermitida -> rolePermitida.equalsIgnoreCase(role));

                    if (!possuiPermissao) {
                        return onError(exchange, "Acesso proibido: falta de permissão", HttpStatus.FORBIDDEN);
                    }
                }

                // Cria uma cópia da requisição inserindo as informações nos headers HTTP
                ServerHttpRequest modifiedRequest = request.mutate()
                        .header("X-User-Email", email)
                        .header("X-User-Role", role)
                        .build();

                return chain.filter(exchange.mutate().request(modifiedRequest).build());

            } catch (Exception e) {
                // Captura qualquer erro de token expirado ou assinatura inválida
                return onError(exchange, "Token inválido ou expirado", HttpStatus.UNAUTHORIZED);
            }
        };
    }

    // estruturar o erro em Json
    private Mono<Void> onError(ServerWebExchange exchange, String mensagem, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        
        // Concatenação manual para evitar a criação de um DTO específico de erro
        String jsonErro = String.format(
                "{\"timestamp\":\"%s\",\"status\":%d,\"erro\":\"%s\",\"mensagem\":\"%s\",\"caminho\":\"%s\"}",
                java.time.LocalDateTime.now(), httpStatus.value(), httpStatus.name(), mensagem, exchange.getRequest().getURI().getPath()
        );
        
        return response.writeWith(Mono.just(response.bufferFactory().wrap(jsonErro.getBytes(StandardCharsets.UTF_8))));
    }

    // Classe necessária para mapear as propriedades dinâmicas de rotas do Gateway
    public static class Config {
        private List<String> rolesPermitidas;

        public List<String> getRolesPermitidas() { return rolesPermitidas; }
        public void setRolesPermitidas(List<String> rolesPermitidas) { this.rolesPermitidas = rolesPermitidas; }
    }
}
