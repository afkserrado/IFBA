package ifba.inf011.q1.classes.Documentos;

import java.util.Set;

import ifba.inf011.q1.classes.Assinatura;
import ifba.inf011.q1.classes.error.FWDocumentException;
import ifba.inf011.q1.enums.Privacidade;
import ifba.inf011.q1.interfaces.IDocumento;
import ifba.inf011.q1.interfaces.IOperador;

public class DocumentoAtestado implements IDocumento {
    
    private IOperador proprietario;
    private Privacidade privacidade;
    private Set<Assinatura> assinaturas;
    private String numero;
    private String conteudo;

    public DocumentoAtestado() {
        System.out.println("Documento criado: " + this.getClass().getSimpleName());
    }

    @Override
    public void inicializar(IOperador proprietario, Privacidade privacidade) throws FWDocumentException {
        if(proprietario == null || privacidade == null) {
            throw new FWDocumentException();
        }
        
        this.proprietario = proprietario;
        this.privacidade = privacidade;
    }

    @Override
    public void adicionarAssinatura(Assinatura assinatura) {
        this.assinaturas.add(assinatura);
    }

    @Override
    public String getNumero() {
        return numero;
    }

    @Override
    public String getConteudo() {
        return conteudo;
    }

    @Override
    public IOperador getProprietario() {
        return proprietario;
    }

    @Override
    public Privacidade getPrivacidade() {
        return privacidade;
    }

    @Override
    public Set<Assinatura> getAssinaturas() {
        return assinaturas;
    }
}
