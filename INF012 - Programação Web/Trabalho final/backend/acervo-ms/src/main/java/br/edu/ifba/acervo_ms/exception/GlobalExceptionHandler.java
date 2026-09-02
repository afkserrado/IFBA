package br.edu.ifba.acervo_ms.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ifba.acervo_ms.dto.ErroResponseDTO;
import br.edu.ifba.acervo_ms.dto.ErroValidationDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<ErroResponseDTO> livroNaoEncontrado(
        LivroNaoEncontradoException ex,
        HttpServletRequest request
    ) {
        
        ErroResponseDTO resposta = new ErroResponseDTO(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidationDTO> erroValidacao(
        MethodArgumentNotValidException ex
    ) {

        Map<String, List<String>> campos = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(erro -> 
            campos.computeIfAbsent(erro.getField(), k -> new ArrayList<>())
                  .add(erro.getDefaultMessage())
        );

        ErroValidationDTO resposta = new ErroValidationDTO(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            campos
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resposta);
    }

    @ExceptionHandler({LivroIndisponivelException.class, OperacaoNaoPermitidaException.class})
    public ResponseEntity<ErroResponseDTO> regraNegocio(
        RuntimeException ex,
        HttpServletRequest request
    ) {
        
        ErroResponseDTO resposta = new ErroResponseDTO(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(resposta);
    }
}
