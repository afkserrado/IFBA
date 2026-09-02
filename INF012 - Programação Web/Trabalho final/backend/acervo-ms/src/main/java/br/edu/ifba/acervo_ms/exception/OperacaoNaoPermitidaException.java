package br.edu.ifba.acervo_ms.exception;

public class OperacaoNaoPermitidaException extends RuntimeException {

    public OperacaoNaoPermitidaException() {
        super("Operação não permitida.");
    }

    public OperacaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}
