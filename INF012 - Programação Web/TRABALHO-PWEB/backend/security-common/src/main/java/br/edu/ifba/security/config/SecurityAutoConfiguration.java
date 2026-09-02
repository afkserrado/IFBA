package br.edu.ifba.security.config;

import br.edu.ifba.security.feign.FeignJwtInterceptor;
import br.edu.ifba.security.jwt.JwtAuthenticationFilter;
import br.edu.ifba.security.jwt.JwtService;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AutoConfiguration
@EnableMethodSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@Import(FeignJwtInterceptor.class)
public class SecurityAutoConfiguration {

        @Bean
        JwtService jwtService(SecurityProperties properties) {
                return new JwtService(properties.getJwtSecret());
        }

        @Bean
        JwtAuthenticationFilter jwtAuthenticationFilter(
                        JwtService jwtService) {

                return new JwtAuthenticationFilter(jwtService);
        }

        @Bean
        SecurityFilterChain securityFilterChain(
                        HttpSecurity http,
                        JwtAuthenticationFilter jwtAuthenticationFilter,
                        SecurityProperties securityProperties) throws Exception {

                return http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                securityProperties.getPublicPaths()
                                                                                .toArray(new String[0]))
                                                .permitAll()
                                                .requestMatchers(
                                                                "/swagger-ui/**",
                                                                "/v3/api-docs/**",
                                                                "/swagger-ui.html")
                                                .permitAll()
                                                .anyRequest()
                                                .authenticated())
                                .addFilterBefore(
                                                jwtAuthenticationFilter,
                                                UsernamePasswordAuthenticationFilter.class)
                                .build();
        }
}