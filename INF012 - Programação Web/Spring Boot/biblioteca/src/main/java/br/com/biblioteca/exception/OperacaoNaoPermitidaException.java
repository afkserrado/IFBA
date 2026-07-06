package br.com.biblioteca.exception;

public class OperacaoNaoPermitidaException extends RuntimeException {

    public OperacaoNaoPermitidaException() {
        super("Operação não permitida");
    }

    public OperacaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}