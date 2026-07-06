package br.com.biblioteca.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.biblioteca.dto.EstatisticasResponseDto;
import br.com.biblioteca.service.EstatisticasService;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticasController {

    private final EstatisticasService estatisticasService;

    public EstatisticasController(EstatisticasService estatisticasService) {
        this.estatisticasService = estatisticasService;
    }

    @GetMapping
    public ResponseEntity<EstatisticasResponseDto> gerarEstatisticas() {
        return ResponseEntity.ok(estatisticasService.gerarEstatisticas());
    }
}