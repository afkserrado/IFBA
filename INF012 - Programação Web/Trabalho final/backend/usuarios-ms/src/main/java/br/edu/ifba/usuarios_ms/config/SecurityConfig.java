package br.edu.ifba.usuarios_ms.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.security.Keys;

/*
 * Classe central de configuração de segurança do usuarios-ms.
 *
 * Ela tem as seguintes responsabilidades:
 *
 * 1. Criar o PasswordEncoder usado para hash e comparação de senhas.
 * 2. Configurar o JwtDecoder responsável por validar os tokens recebidos.
 * 3. Definir as rotas públicas e as rotas que exigem autenticação.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*
     * Cria o bean responsável por codificar senhas.
     *
     * O AuthService injeta este bean para comparar
     * a senha digitada no login com o hash salvo no banco.
     *
     * O UsuarioService também pode injetá-lo para gerar
     * o hash no momento do cadastro.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        /*
         * BCrypt é um algoritmo de hash para senhas.
         *
         * Ele adiciona um salt aleatório e torna inviável
         * recuperar a senha original a partir do hash.
         */
        return new BCryptPasswordEncoder();
    }

    /*
     * Define a cadeia de filtros de segurança.
     *
     * A partir desta etapa, o serviço valida JWTs recebidos
     * no cabeçalho Authorization.
     *
     * Regras atuais:
     * - POST /api/v1/auth/login permanece público;
     * - POST /api/v1/usuarios permanece público;
     * - qualquer outra rota exige um JWT válido.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        JwtDecoder jwtDecoder,
        JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {

        http
            /*
             * Desabilita CSRF temporariamente.
             *
             * CSRF é relevante principalmente para aplicações que usam
             * autenticação por cookie/sessão. A API usará Bearer Token,
             * então essa proteção não é necessária neste momento.
             */
            .csrf(csrf -> csrf.disable())

            /*
             * Conecta o Resource Server JWT à cadeia de filtros.
             *
             * O Spring usará o bean JwtDecoder criado nesta mesma classe
             * para validar a assinatura, o emissor e a expiração do token.
             */
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder)
                    .jwtAuthenticationConverter(jwtAuthenticationConverter)
                )
            )

            /*
             * Define as regras de autorização.
             *
             * As rotas de autenticação e cadastro são públicas.
             * Todas as demais exigem um token válido.
             */
            .authorizeHttpRequests(auth -> auth
                // Login e cadastro permanecem públicos.
                // O cadastro sempre cria usuários com papel USER.
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/login", "/api/v1/usuarios")
                    .permitAll()

                // Apenas ADMIN pode listar todos os usuários.
                .requestMatchers(HttpMethod.GET, "/api/v1/usuarios")
                    .hasRole("ADMIN")

                // Apenas ADMIN pode buscar usuários por e-mail.
                .requestMatchers(HttpMethod.GET, "/api/v1/usuarios/busca-email")
                    .hasRole("ADMIN")

                // Apenas ADMIN pode buscar usuários por ID.
                // Um USER não pode consultar nem o próprio perfil por ID.
                .requestMatchers(HttpMethod.GET, "/api/v1/usuarios/*")
                    .hasRole("ADMIN")

                // Endpoint interno utilizado pelo emprestimos-ms.
                // Como o emprestimos-ms propaga o token do funcionário ADMIN,
                // apenas ADMIN pode validar a situação cadastral de um usuário.
                .requestMatchers(HttpMethod.GET, "/api/v1/usuarios/*/validar-situacao")
                    .hasRole("ADMIN")

                // Qualquer outra requisição exige autenticação.
                // As regras de acesso à própria conta serão definidas
                // com @PreAuthorize no UsuarioController.
                .anyRequest().authenticated()
            );

        /*
         * Constrói e retorna a cadeia de filtros configurada.
         */
        return http.build();
    }

    /*
     * Registra o componente responsável por validar os JWTs.
     *
     * Usa o mesmo segredo e a mesma conversão para bytes
     * utilizados pelo JwtService na emissão do token.
     */
    @Bean
    public JwtDecoder jwtDecoder(
        @Value("${app.security.jwt.secret}") String segredo,
        @Value("${app.security.jwt.issuer}") String emissor
    ) {
        /*
         * Converte o texto do segredo para bytes UTF-8.
         *
         * O JwtService atual também utiliza os bytes do texto. 
         * Os dois lados precisam usar exatamente o mesmo 
         * material de chave.
         */
        SecretKey chaveAssinatura = Keys.hmacShaKeyFor(
            segredo.getBytes(StandardCharsets.UTF_8)
        );

        /*
         * Cria o decoder com a chave compartilhada.
         *
         * Aceita explicitamente HS256, o mesmo algoritmo
         * usado pelo JwtService para assinar os tokens.
         */
        NimbusJwtDecoder decoder = NimbusJwtDecoder
            .withSecretKey(chaveAssinatura)
            .macAlgorithm(MacAlgorithm.HS256)
            .build();

        /*
         * Adiciona a verificação de "iss" aos validadores padrão,
         * incluindo as verificações temporais de "exp" e "nbf".
         *
         * "iat" indica quando o token foi emitido, mas não deve-se
         * afirmar que seu valor é validado automaticamente aqui.
         */
        decoder.setJwtValidator(
            JwtValidators.createDefaultWithIssuer(emissor)
        );

        return decoder;
    }
}