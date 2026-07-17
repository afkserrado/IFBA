package br.edu.ifba.usuarios_ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    // O Spring injeta o seu filtro personalizado automaticamente aqui
    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Dentro do seu SecurityConfig.java, atualize o método securityFilterChain:
                .authorizeHttpRequests(authorize -> authorize
                        // Rota de login pública
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Permite que qualquer pessoa se cadastre no sistema (o Service filtrará se for
                        // tentar criar ADMIN)
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // As demais rotas de consulta, alteração e deleção exigem apenas autenticação
                        // genérica,
                        // pois a segurança fina de quem altera quem foi movida para dentro do
                        // UsuarioService
                        .anyRequest().authenticated())
                // Injeta o seu SecurityFilter ANTES do filtro padrão do Spring
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
