package br.edu.ifba.emprestimos_ms.exception;

public class OperacaoNaoPermitidaException extends RuntimeException {

    public OperacaoNaoPermitidaException() {
        super("Operação não permitida.");
    }

    public OperacaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }

    // Preserva a exceção do Feign como causa da exceção local
    public OperacaoNaoPermitidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}