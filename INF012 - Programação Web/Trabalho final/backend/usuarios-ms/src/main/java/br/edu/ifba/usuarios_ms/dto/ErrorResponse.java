package br.edu.ifba.usuarios_ms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta propriedades nulas no JSON de saída
public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String erro,
    String mensagem,
    Map<String, String> campos,
    String caminho
) {
    // Construtor para erros convencionais (404, 409, 401, 403, 500)
    public ErrorResponse(int status, String erro, String mensagem, String caminho) {
        this(LocalDateTime.now(), status, erro, mensagem, null, caminho);
    }

    // Construtor para erros de validação de campos (400)
    public ErrorResponse(int status, String erro, Map<String, String> campos, String caminho) {
        this(LocalDateTime.now(), status, erro, null, campos, caminho);
    }
}
