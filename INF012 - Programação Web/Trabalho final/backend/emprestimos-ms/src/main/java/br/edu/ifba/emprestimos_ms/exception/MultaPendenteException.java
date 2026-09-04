package br.edu.ifba.emprestimos_ms.exception;

public class MultaPendenteException extends RuntimeException {

    public MultaPendenteException() {
        super("O usuário possui multas pendentes e não pode realizar novos empréstimos.");
    }

    public MultaPendenteException(String mensagem) {
        super(mensagem);
    }
}