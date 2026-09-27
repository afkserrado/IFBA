package br.edu.ifba.usuarios_ms.exception;

public class ServicoIndisponivelException extends RuntimeException {
    
    public ServicoIndisponivelException() {
        super("Serviço de empréstimos indisponível no momento.");
    }

    public ServicoIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public ServicoIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
