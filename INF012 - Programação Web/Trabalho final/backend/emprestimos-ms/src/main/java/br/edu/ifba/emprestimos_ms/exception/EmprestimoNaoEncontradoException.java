package br.edu.ifba.emprestimos_ms.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
	    public EmprestimoNaoEncontradoException(String mensagem) { 
	    	super(mensagem);
	}
}
