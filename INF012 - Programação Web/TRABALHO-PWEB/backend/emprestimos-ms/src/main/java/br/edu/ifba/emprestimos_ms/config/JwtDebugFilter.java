package br.edu.ifba.emprestimos_ms.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtDebugFilter extends OncePerRequestFilter {

    public JwtDebugFilter() {
        System.out.println(">>> JwtDebugFilter carregado");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("================================");
        System.out.println("REQUEST: " + request.getMethod() + " " + request.getRequestURI());
        System.out.println("AUTH HEADER: " + request.getHeader("Authorization"));
        System.out.println("================================");

        filterChain.doFilter(request, response);
    }
}