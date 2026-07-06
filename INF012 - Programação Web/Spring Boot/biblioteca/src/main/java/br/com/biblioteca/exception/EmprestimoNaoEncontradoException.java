package br.com.biblioteca.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    
    public EmprestimoNaoEncontradoException() {
        super("Empréstimo não encontrado");
    }

    public EmprestimoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
