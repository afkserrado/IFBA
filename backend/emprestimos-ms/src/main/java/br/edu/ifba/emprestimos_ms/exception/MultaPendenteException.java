package br.edu.ifba.emprestimos_ms.exception;

public class MultaPendenteException extends RuntimeException {
    public MultaPendenteException(String mensagem) {
        super(mensagem);
    }
}
