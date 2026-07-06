package br.com.biblioteca.exception;

public class LivroIndisponivelException extends RuntimeException {

    public LivroIndisponivelException() {
        super("Livro sem exemplares disponíveis");
    }

    public LivroIndisponivelException(String mensagem) {
        super(mensagem);
    }
}