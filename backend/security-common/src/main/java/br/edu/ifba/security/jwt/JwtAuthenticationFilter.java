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
                 * Caso não exista token,
                 * deixa o Spring Security decidir
                 * se a rota é pública ou privada.
                 */
                if (authorization == null
                                || !authorization.startsWith("Bearer ")) {

                        filterChain.doFilter(request, response);
                        return;
                }

                try {
                        String token = authorization.substring(7);

                        Claims claims = jwtService.validarToken(token);

                        String email = jwtService.getEmail(claims);

                        String role = jwtService.getRole(claims);

                        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                                        new SimpleGrantedAuthority(
                                                        "ROLE_" + role));

                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                        email,
                                        null,
                                        authorities);

                        SecurityContextHolder
                                        .getContext()
                                        .setAuthentication(authentication);
                } catch (Exception e) {

                        SecurityContextHolder.clearContext();
                }

                filterChain.doFilter(request, response);
        }
}