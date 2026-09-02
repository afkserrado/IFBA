package br.edu.ifba.usuarios_ms.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifba.usuarios_ms.dto.LoginRequestDTO;
import br.edu.ifba.usuarios_ms.dto.TokenResponseDTO;
import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    // Usamos @Lazy no AuthenticationManager para evitar problemas de dependência circular
    public AutenticacaoService(UsuarioRepository usuarioRepository, 
                               TokenService tokenService, 
                               @Lazy AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + username));
    }

    public TokenResponseDTO autenticar(LoginRequestDTO dto) {
        // Cria o token de credenciais não autenticadas
        var credentialsToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        
        Authentication authentication = authenticationManager.authenticate(credentialsToken);
        
        Usuario usuario = (Usuario) authentication.getPrincipal();
        
        String tokenGerado = tokenService.gerarToken(usuario);
        return new TokenResponseDTO(usuario, tokenGerado);
    }
}
