package br.edu.ifba.blog.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ifba.blog.dtos.DadosErroValidacao;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorExcecoes {
    
    @ExceptionHandler(
        EntityNotFoundException.class
    )
    public ResponseEntity<?> tratarErro404NotFound() {
        return ResponseEntity
            .notFound()
            .build();
    }

    @ExceptionHandler(
        MethodArgumentNotValidException.class
    )   
    public ResponseEntity<?> tratarErro400NotValid(MethodArgumentNotValidException ex) {

        var erros = ex.getFieldErrors();

        return ResponseEntity
                .badRequest()
                .body(
                    erros
                        .stream()
                        .map(DadosErroValidacao::new)
                        .toList()
                );
    }
}
