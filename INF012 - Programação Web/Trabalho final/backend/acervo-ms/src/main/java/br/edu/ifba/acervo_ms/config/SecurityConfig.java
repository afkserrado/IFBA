
package br.edu.ifba.acervo_ms.config;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração central de segurança do acervo-ms.
 *
 * O microsserviço atua como Resource Server: recebe um JWT emitido pelo
 * usuarios-ms e valida sua assinatura, emissor e validade antes de liberar
 * o acesso às rotas protegidas.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define a cadeia de filtros de segurança da aplicação.
     *
     * Regras adotadas:
     * - Documentação do Swagger permanece pública.
     * - Consultas de livros (GET) permanecem públicas.
     * - Operações de criação, atualização e remoção exigem um JWT válido.
     * - Endpoints internos de estoque também exigem um JWT válido.
     */
    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity http, 
        JwtDecoder jwtDecoder,
        JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {

        http
            // A API utiliza Bearer Token e não autenticação por cookie/sessão.
            // Portanto, a proteção CSRF é desativada neste contexto.
            .csrf(csrf -> csrf.disable())

            // Configura o serviço como Resource Server JWT.
            // O Spring Security usará o JwtDecoder definido abaixo para validar
            // o token enviado no cabeçalho Authorization.
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder)
                    .jwtAuthenticationConverter(jwtAuthenticationConverter)
                )
            )

            // Define quais requisições são públicas e quais exigem autenticação.
            .authorizeHttpRequests(auth -> auth
                // Libera o acesso à documentação gerada pelo Springdoc OpenAPI.
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Mantém as consultas ao acervo públicas.
                .requestMatchers(HttpMethod.GET, "/api/v1/livros/**").permitAll()

                // Exige o papel ADMIN para cadastrar livros.
                .requestMatchers(HttpMethod.POST, "/api/v1/livros")
                    .hasRole("ADMIN")

                // Exige o papel ADMIN para atualizar livros.
                .requestMatchers(HttpMethod.PUT, "/api/v1/livros/**")
                    .hasRole("ADMIN")

                // Exige o papel ADMIN para remover livros.
                .requestMatchers(HttpMethod.DELETE, "/api/v1/livros/**")
                    .hasRole("ADMIN")

                // Exige o papel ADMIN para reduzir o estoque durante um empréstimo.
                .requestMatchers(HttpMethod.POST, "/api/v1/livros/*/reduzir-estoque")
                    .hasRole("ADMIN")

                // Exige o papel ADMIN para aumentar o estoque durante uma devolução.
                .requestMatchers(HttpMethod.POST, "/api/v1/livros/*/aumentar-estoque")
                    .hasRole("ADMIN")

                // Exige autenticação para qualquer outra requisição
                // que não tenha sido liberada ou restrita anteriormente.
                .anyRequest().authenticated()
            );

        return http.build();
    }

    /**
     * Cria o componente responsável por validar os JWTs recebidos.
     *
     * É utilizado o mesmo segredo e o mesmo emissor configurados no usuarios-ms,
     * pois o algoritmo HS256 é simétrico: a chave que assina o token também
     * é utilizada para verificar sua assinatura.
     */
    @Bean
    JwtDecoder jwtDecoder(
            @Value("${app.security.jwt.secret}") String segredo,
            @Value("${app.security.jwt.issuer}") String emissor) {

        // Converte o segredo configurado em bytes UTF-8.
        // A conversão precisa ser idêntica à realizada pelo JwtService do usuarios-ms.
        SecretKey chaveAssinatura = new SecretKeySpec(
            segredo.getBytes(StandardCharsets.UTF_8), "HmacSHA256"
        );

        // Cria o decoder aceitando explicitamente HS256,
        // algoritmo utilizado na emissão dos tokens.
        NimbusJwtDecoder decoder = NimbusJwtDecoder
            .withSecretKey(chaveAssinatura)
            .macAlgorithm(MacAlgorithm.HS256)
            .build();

        // Valida o emissor (iss) e as verificações temporais padrão,
        // como a expiração do token.
        decoder.setJwtValidator(
            JwtValidators.createDefaultWithIssuer(emissor)
        );

        return decoder;
    }
}