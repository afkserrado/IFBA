package br.edu.ifba.usuarios_ms.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException() {
        super("Usuário não encontrado.");
    }

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}