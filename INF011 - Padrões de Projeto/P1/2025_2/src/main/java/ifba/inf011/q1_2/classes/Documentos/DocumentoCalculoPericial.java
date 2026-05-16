package ifba.inf011.q1_2.classes.Documentos;

import java.util.HashSet;
import java.util.Set;

import ifba.inf011.q1_2.classes.Assinatura;
import ifba.inf011.q1_2.classes.error.FWDocumentException;
import ifba.inf011.q1_2.enums.Privacidade;
import ifba.inf011.q1_2.interfaces.IDocumentoPrototipavel;
import ifba.inf011.q1_2.interfaces.IOperador;

// Adaptado para atender a questão 2

// Concrete Product
public class DocumentoCalculoPericial implements IDocumentoPrototipavel {
    
    private IOperador proprietario;
    private Privacidade privacidade;
    private Set<Assinatura> assinaturas;
    private String numero;
    private String conteudo;

    public DocumentoCalculoPericial() {
        this.assinaturas = new HashSet<>();
        System.out.println("Documento criado: " + this.getClass().getSimpleName());
    }

    private DocumentoCalculoPericial(DocumentoCalculoPericial prototipo) {
        this.proprietario = null;
        this.privacidade = null;
        this.assinaturas = new HashSet<>();
        this.numero = prototipo.numero;
        this.conteudo = prototipo.conteudo;
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

    @Override
    // Retorno covariante
    public IDocumentoPrototipavel clone() {
        return new DocumentoCalculoPericial(this);
    }
}
