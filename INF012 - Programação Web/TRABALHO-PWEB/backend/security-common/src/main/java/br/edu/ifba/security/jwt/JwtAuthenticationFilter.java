package br.edu.ifba.security.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtService jwtService;

        public JwtAuthenticationFilter(JwtService jwtService) {
                this.jwtService = jwtService;
        }

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {

                String authorization = request.getHeader("Authorization");

                System.out.println(
                                "RECEBIDO NO EMPRESTIMOS: "
                                                + authorization);

                /*
                 * Se não veio um token Bearer, o Spring Security continua
                 * normalmente e decide se a rota exige autenticação.
                 */
                if (authorization == null
                                || !authorization.startsWith("Bearer ")) {

                        filterChain.doFilter(request, response);
                        return;
                }

                try {

                        // Remove o "Bearer " e fica somente com o JWT
                        String token = authorization.substring(7);

                        // Valida o token e recupera todas as claims
                        Claims claims = jwtService.validarToken(token);

                        // Recupera as informações que foram gravadas no JWT
                        Long id = jwtService.getUserId(claims);
                        String email = jwtService.getEmail(claims);
                        String role = jwtService.getRole(claims);

                        // Cria um objeto representando o usuário autenticado
                        AuthenticatedUser principal = new AuthenticatedUser(id, email);

                        // Converte a role para o formato esperado pelo Spring
                        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                                        new SimpleGrantedAuthority(
                                                        "ROLE_" + role));

                        // Cria o Authentication que ficará disponível durante a requisição
                        UsernamePasswordAuthenticationToken authentication =
                                        new UsernamePasswordAuthenticationToken(
                                                        principal,
                                                        null,
                                                        authorities);

                        // Salva o usuário autenticado no contexto de segurança
                        SecurityContextHolder
                                        .getContext()
                                        .setAuthentication(authentication);

                } catch (Exception e) {

                        // Se o token for inválido, remove qualquer autenticação
                        SecurityContextHolder.clearContext();
                }

                // Continua para o próximo filtro da cadeia
                filterChain.doFilter(request, response);
        }
}