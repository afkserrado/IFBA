package br.edu.ifba.acervo_ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

/**
 * Configuração responsável por converter a claim "role" do JWT
 * em uma autoridade reconhecida pelo Spring Security.
 *
 * O usuarios-ms emite tokens com a claim:
 * "role": "USER" ou "role": "ADMIN"
 *
 * O Spring Security trabalha com autoridades no formato:
 * "ROLE_USER" ou "ROLE_ADMIN"
 *
 * Esta configuração realiza essa conversão.
 */
@Configuration
public class JwtAuthenticationConverterConfig {

    /**
     * Cria o conversor utilizado pelo Resource Server para transformar
     * as claims do JWT em autoridades do Spring Security.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        // Procura a claim chamada "role" dentro do payload do JWT.
        // Obtém o valor dela, por exemplo "USER" ou "ADMIN".
        // Adiciona o prefixo "ROLE_" ao valor encontrado.
        // Transforma o resultado em uma autoridade reconhecida pelo Spring Security.

        // Lê uma claim do JWT e transforma seu valor em uma autoridade do Spring Security.
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("role");

        // Adiciona automaticamente o prefixo "ROLE_" ao valor da claim.
        // Exemplo: "USER" passa a ser interpretado como "ROLE_USER".
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        // Coordena a conversão do JWT em um objeto de autenticação, incluindo as autoridades geradas.
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        // Associa o conversor de claims ao conversor de autenticação.
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return jwtAuthenticationConverter;
    }
}