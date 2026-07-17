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

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        TokenResponseDTO response = autenticacaoService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
