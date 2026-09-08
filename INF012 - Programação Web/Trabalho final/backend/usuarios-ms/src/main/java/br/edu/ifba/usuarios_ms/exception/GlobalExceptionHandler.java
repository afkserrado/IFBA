package br.edu.ifba.usuarios_ms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ifba.usuarios_ms.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // dados inválidos 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errosMapeados = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Campo inválido",
                        (antigo, novo) -> antigo // aqui eu optei por evitar duplicidade se houver mais de uma falha no mesmo campo
                ));

        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "DADOS_INVALIDOS",
                errosMapeados,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "DADOS_INVALIDOS",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // recurso inexistente
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                "NOT_FOUND",
                ex.getMessage(),
                request.getRequestURI()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // violação de regra de negócio
    @ExceptionHandler({BusinessException.class, IllegalStateException.class})
    public ResponseEntity<ErrorResponseDTO> handleConflict(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "CONFLITO",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // erro inesperado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneralException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO erro = new ErrorResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde.",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
