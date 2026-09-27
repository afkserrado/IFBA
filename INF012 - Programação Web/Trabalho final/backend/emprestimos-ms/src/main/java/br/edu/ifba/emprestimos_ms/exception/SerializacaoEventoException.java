package br.edu.ifba.emprestimos_ms.exception;

public class SerializacaoEventoException extends RuntimeException {
    
    public SerializacaoEventoException() {
        super("Usuário não encontrado.");
    }

    public SerializacaoEventoException(String mensagem) {
        super(mensagem);
    }

    // Preserva a exceção do Feign como causa da exceção local
    public SerializacaoEventoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
