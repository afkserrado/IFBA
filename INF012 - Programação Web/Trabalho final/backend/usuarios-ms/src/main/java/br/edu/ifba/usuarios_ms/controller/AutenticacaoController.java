package br.edu.ifba.usuarios_ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.usuarios_ms.dto.LoginRequestDTO;
import br.edu.ifba.usuarios_ms.dto.TokenResponseDTO;
import br.edu.ifba.usuarios_ms.service.AutenticacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoint para autenticação de usuários e geração de tokens JWT")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    @Operation(
        summary = "Realiza o login do usuário", 
        description = "Valida as credenciais enviadas (e-mail e senha) e retorna um token JWT válido para acessar as rotas protegidas do sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso. Retorna o token JWT de acesso.",
            content = @Content(schema = @Schema(implementation = TokenResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados da requisição mal formatados ou inválidos", content = @Content),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas (e-mail ou senha incorretos)", content = @Content)
    })
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        TokenResponseDTO response = autenticacaoService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
