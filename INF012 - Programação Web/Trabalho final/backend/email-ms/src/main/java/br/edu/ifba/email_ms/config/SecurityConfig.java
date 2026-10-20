package br.edu.ifba.email_ms.config;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração central de segurança do email-ms.
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
     * - O envio de e-mails exige um JWT válido.
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

            // Não cria nem utiliza sessão HTTP para manter a autenticação.
            // Cada requisição protegida deve apresentar seu próprio Bearer Token.
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

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

                // Exige o papel ADMIN para enviar e-mails.
                // Apenas o serviço de notificações, autenticado com um token ADMIN,
                // deve conseguir acionar o envio de mensagens.
                .requestMatchers(HttpMethod.POST, "/emails/enviar")
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