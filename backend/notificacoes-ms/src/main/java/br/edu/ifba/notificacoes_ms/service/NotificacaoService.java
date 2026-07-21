package br.edu.ifba.notificacoes_ms.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificacaoService {

    private final ObjectMapper objectMapper;

    public NotificacaoService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}