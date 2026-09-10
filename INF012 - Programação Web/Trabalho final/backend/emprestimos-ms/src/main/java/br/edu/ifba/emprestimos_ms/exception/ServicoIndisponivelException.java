package br.edu.ifba.emprestimos_ms.exception;

public class ServicoIndisponivelException extends RuntimeException {
    
    public ServicoIndisponivelException(String mensagem) {
        super(mensagem);
    }

    public ServicoIndisponivelException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
