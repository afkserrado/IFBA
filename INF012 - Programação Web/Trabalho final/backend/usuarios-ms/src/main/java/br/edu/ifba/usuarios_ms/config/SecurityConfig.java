package br.edu.ifba.usuarios_ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * Classe central de configuração de segurança do usuarios-ms.
 *
 * Por enquanto, ela tem duas responsabilidades:
 *
 * 1. Criar o PasswordEncoder usado para hash e comparação de senhas.
 * 2. Manter todas as rotas públicas temporariamente,
 *    para permitir testar o login antes de proteger os endpoints.
 */
@Configuration
@EnableWebSecurity
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
     * Esta configuração é temporária.
     *
     * anyRequest().permitAll() permite que todos os endpoints
     * continuem acessíveis enquanto implementamos o login.
     *
     * Nas próximas etapas, substituiremos esta regra por:
     * - rotas públicas, como login e cadastro;
     * - rotas autenticadas;
     * - rotas restritas a ADMIN.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            /*
             * Desabilita CSRF temporariamente.
             *
             * CSRF é relevante principalmente para aplicações que usam
             * autenticação por cookie/sessão. Nossa API usará Bearer Token,
             * então essa proteção não é necessária neste momento.
             */
            .csrf(csrf -> csrf.disable())

            /*
             * Define as regras de autorização.
             *
             * Por enquanto, qualquer requisição é permitida.
             */
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        /*
         * Constrói e retorna a cadeia de filtros configurada.
         */
        return http.build();
    }
}