package br.edu.ifba.usuarios_ms.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.edu.ifba.usuarios_ms.dto.LoginRequestDTO;
import br.edu.ifba.usuarios_ms.dto.TokenResponseDTO;
import br.edu.ifba.usuarios_ms.entity.Usuario;
import br.edu.ifba.usuarios_ms.exception.CredenciaisInvalidasException;
import br.edu.ifba.usuarios_ms.repository.UsuarioRepository;
import br.edu.ifba.usuarios_ms.security.JwtService;

/*
 * Serviço responsável pela autenticação do usuário.
 *
 * Fluxo:
 * LoginRequestDTO
 *     ↓
 * Busca o usuário pelo e-mail
 *     ↓
 * Compara a senha informada com o hash do banco
 *     ↓
 * Gera o JWT
 *     ↓
 * Retorna TokenResponseDTO
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    // Codificador usado para comparar a senha digitada com o hash armazenado
    private final PasswordEncoder passwordEncoder;

    // Serviço responsável por gerar o JWT
    private final JwtService jwtService;

    /*
     * Construtor com injeção de dependências.
     *
     * O Spring entrega automaticamente os beans necessários:
     * UsuarioRepository, PasswordEncoder e JwtService.
     */
    public AuthService(
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /*
     * Realiza o login do usuário.
     *
     * Este método não salva dados e não altera o banco;
     * por isso, readOnly = true.
     */
    @Transactional(readOnly = true)
    public TokenResponseDTO login(LoginRequestDTO dto) {

        /*
         * Busca o usuário pelo e-mail informado no login.
         *
         * Se o e-mail não existir, lança CredenciaisInvalidasException.
         */
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
            .orElseThrow(() -> new CredenciaisInvalidasException(
                "E-mail ou senha inválidos."
            ));

        /*
         * Compara a senha digitada com o hash armazenado no banco.
         *
         * Importante:
         * - dto.senha() é a senha em texto puro enviada pelo cliente.
         * - usuario.getSenha() é o hash BCrypt salvo no banco.
         *
         * O BCrypt extrai o salt do hash e verifica se a senha
         * informada gera o mesmo resultado.
         */
        boolean senhaValida = passwordEncoder.matches(
            dto.senha(),
            usuario.getSenha()
        );

        /*
         * Se a senha não corresponder ao hash,
         * o login é recusado.
         */
        if (!senhaValida) {
            throw new CredenciaisInvalidasException(
                "E-mail ou senha inválidos."
            );
        }

        /*
         * Somente após confirmar que o usuário existe
         * e que a senha está correta, o JWT é gerado.
         */
        String token = jwtService.gerarToken(usuario);

        /*
         * Retorna o token e o tipo de autenticação.
         *
         * O frontend deverá usar:
         * Authorization: Bearer <token>
         */
        return new TokenResponseDTO(
            token,
            "Bearer"
        );
    }
}