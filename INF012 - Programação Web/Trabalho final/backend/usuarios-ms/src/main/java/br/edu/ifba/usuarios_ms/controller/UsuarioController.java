package br.edu.ifba.usuarios_ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.usuarios_ms.dto.UsuarioRequestDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioResponseDTO;
import br.edu.ifba.usuarios_ms.dto.UsuarioUpdateRequestDTO;
import br.edu.ifba.usuarios_ms.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de cadastro de leitores e usuários do sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo usuário", description = "Cria uma conta de usuário no sistema com validação de credenciais e e-mail único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos ou e-mail já cadastrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.criarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário por ID", description = "Retorna os detalhes completos do perfil de um usuário a partir do seu identificador numérico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário localizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado para o ID informado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "Identificador exclusivo do usuário") @PathVariable Long id) {
        UsuarioResponseDTO response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista contendo todos os usuários registrados na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content)
    })
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodos() {
        List<UsuarioResponseDTO> response = usuarioService.buscarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um usuário", description = "Modifica os dados cadastrais permitidos de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário updated com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados fornecidos inválidos", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado para alteração desse perfil", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não localizado", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> editar(
            @Parameter(description = "ID do usuário a ser editado") @PathVariable Long id,
            @RequestBody @Valid UsuarioUpdateRequestDTO dto) {
        UsuarioResponseDTO response = usuarioService.editar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um usuário do sistema", description = "Deleta a conta do usuário caso ele não possua multas em aberto ou empréstimos ativos pendentes de devolução.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Exclusão recusada por pendências financeiras ou de acervo", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso restrito a administradores", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<Void> remover(
            @Parameter(description = "ID do usuário a ser excluído") @PathVariable Long id) {
        usuarioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busca-email")
    @Operation(summary = "Busca um usuário por e-mail", description = "Localiza o registro de um usuário através do seu endereço de e-mail exato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário localizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado", content = @Content),
            @ApiResponse(responseCode = "404", description = "E-mail não cadastrado no sistema", content = @Content)
    })
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(
            @Parameter(description = "Endereço de e-mail do usuário procurado") @RequestParam String email) {
        UsuarioResponseDTO response = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/validar-situacao")
    @Operation(summary = "Valida existência do usuário", description = "Verifica se existe um usuário cadastrado com o identificador informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    public ResponseEntity<Boolean> validarSituacaoCadastral(
            @Parameter(description = "ID do usuário") @PathVariable Long id) {
        boolean existe = usuarioService.existeUsuario(id);
        return ResponseEntity.ok(existe);
    }
}
