package br.edu.ifba.usuarios_ms.exception;

/*
 * Exceção lançada quando o e-mail não existe
 * ou quando a senha informada não corresponde ao hash armazenado.
 *
 */
public class CredenciaisInvalidasException extends RuntimeException {

    public CredenciaisInvalidasException() {
        super("E-mail ou senha inválidos.");
    }

    public CredenciaisInvalidasException(String mensagem) {
        super(mensagem);
    }
}