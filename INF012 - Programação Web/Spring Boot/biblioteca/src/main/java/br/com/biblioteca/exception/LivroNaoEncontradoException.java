package br.com.biblioteca.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    
    public LivroNaoEncontradoException() {
        super("Livro não encontrado.");
    }

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
