package br.edu.ifba.emprestimos_ms.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    // Exceção de Não Encontrado (404)
    @ExceptionHandler(EmprestimoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EmprestimoNaoEncontradoException ex,
            HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, "RECURSO_NAO_ENCONTRADO", ex.getMessage(), request.getRequestURI());
    }

    //Excecao para violação de regra de negócio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.FORBIDDEN,
                "ACESSO_NEGADO",
                ex.getMessage(),
                request.getRequestURI());
    }

    // Exceção de Multa Pendente (409)
    @ExceptionHandler(MultaPendenteException.class)
    public ResponseEntity<Map<String, Object>> handleMultaPendente(MultaPendenteException ex,
            HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "MULTA_PENDENTE", ex.getMessage(), request.getRequestURI());
    }

    // Regras de negócio em geral (sem estoque, já devolvido, etc.)
    @ExceptionHandler({ IllegalStateException.class, IllegalArgumentException.class })
    public ResponseEntity<Map<String, Object>> handleRegrasNegocio(RuntimeException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, "VIOLACAO_REGRA_NEGOCIO", ex.getMessage(), request.getRequestURI());
    }

    // 4. Validações do DTO Request - @NotNull, @FutureOrPresent
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            campos.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().format(formatter));
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("erro", "DADOS_INVALIDOS");
        response.put("campos", campos);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String erro, String mensagem,
            String caminho) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now().format(formatter));
        response.put("status", status.value());
        response.put("erro", erro);
        response.put("mensagem", mensagem);
        response.put("caminho", caminho);
        return ResponseEntity.status(status).body(response);
    }
}
