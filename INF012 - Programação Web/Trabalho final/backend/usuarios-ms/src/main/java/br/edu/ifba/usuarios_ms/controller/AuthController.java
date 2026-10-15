package br.edu.ifba.usuarios_ms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import br.edu.ifba.usuarios_ms.dto.LoginRequestDTO;
import br.edu.ifba.usuarios_ms.dto.TokenResponseDTO;
import br.edu.ifba.usuarios_ms.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/*
 * Controller responsável pelos endpoints de autenticação.
 *
 * Ele não contém regras de negócio.
 * Sua responsabilidade é:
 *
 * 1. Receber a requisição HTTP;
 * 2. Validar o formato do corpo recebido;
 * 3. Chamar o AuthService;
 * 4. Devolver a resposta HTTP adequada.
 */
@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    // Serviço responsável por validar credenciais e gerar o JWT
    private final AuthService authService;

    /*
     * Construtor com injeção de dependência.
     *
     * O Spring entrega automaticamente uma instância de AuthService.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*
     * Endpoint público de login.
     *
     * Recebe e-mail e senha e retorna um JWT,
     * caso as credenciais sejam válidas.
     */
    @PostMapping("/login")
    @Operation(
        summary = "Realiza login",
        description = "Autentica um usuário por e-mail e senha e retorna um JWT."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos"),
        @ApiResponse(responseCode = "401", description = "E-mail ou senha inválidos")
    })
    public ResponseEntity<TokenResponseDTO> login(
        @RequestBody @Valid LoginRequestDTO dto
    ) {
        /*
         * Delega a autenticação ao AuthService.
         *
         * Se o e-mail não existir ou a senha estiver incorreta,
         * o AuthService lançará CredenciaisInvalidasException.
         */
        TokenResponseDTO resposta = authService.login(dto);

        /*
         * Retorna HTTP 200 com o token no corpo da resposta.
         */
        return ResponseEntity.ok(resposta);
    }
}