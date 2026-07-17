package br.edu.ifba.usuarios_ms.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import br.edu.ifba.usuarios_ms.service.AutenticacaoService;
import br.edu.ifba.usuarios_ms.service.TokenService;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AutenticacaoService autenticacaoService;

    public SecurityFilter(TokenService tokenService, AutenticacaoService autenticacaoService) {
        this.tokenService = tokenService;
        this.autenticacaoService = autenticacaoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = recuperarToken(request);
        
        if (token != null) {
            String email = tokenService.validarToken(token);
            
            if (!email.isEmpty()) {
                
                UserDetails usuario = autenticacaoService.loadUserByUsername(email);

                
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                
                // dessa forma a autenticação é salva no contexto do Spring com todas as suas permissões
                // mais adiante quando se quiser saber as autorizações do usuário basta olhar o contexto
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
