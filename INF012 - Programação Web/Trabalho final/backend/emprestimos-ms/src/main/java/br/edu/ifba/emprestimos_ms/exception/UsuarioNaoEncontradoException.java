package br.edu.ifba.emprestimos_ms.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    
    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    // Preserva a exceção do Feign como causa da exceção local
    public UsuarioNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
