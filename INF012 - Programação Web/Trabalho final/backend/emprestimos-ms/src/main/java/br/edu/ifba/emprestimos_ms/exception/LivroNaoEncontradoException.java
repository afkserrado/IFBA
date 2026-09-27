package br.edu.ifba.emprestimos_ms.exception;

public class LivroNaoEncontradoException extends RuntimeException {
    
    public LivroNaoEncontradoException() {
        super("Livro não encontrado.");
    }

    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    // Preserva a exceção do Feign como causa da exceção local
    public LivroNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
