package br.com.biblioteca.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorExcecoes {
    
    @ExceptionHandler({LivroNaoEncontradoException.class, EmprestimoNaoEncontradoException.class})
    public ResponseEntity<Map<String, Object>> recursoNaoEncontrado(RuntimeException ex) {
        
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.NOT_FOUND.value());
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> erroValidacao(MethodArgumentNotValidException ex) {
        
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(erro -> erros.put(erro.getField(), erro.getDefaultMessage()));

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erros", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler({LivroIndisponivelException.class, OperacaoNaoPermitidaException.class})
    public ResponseEntity<Map<String, Object>> regraNegocio(RuntimeException ex) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.CONFLICT.value());
        resposta.put("mensagem", ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }
}
