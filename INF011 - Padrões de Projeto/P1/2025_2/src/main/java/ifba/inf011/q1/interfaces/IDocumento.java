package ifba.inf011.q1.interfaces;

import java.util.Set;

import ifba.inf011.q1.classes.Assinatura;
import ifba.inf011.q1.classes.error.FWDocumentException;
import ifba.inf011.q1.enums.Privacidade;

public interface IDocumento {
    public void inicializar(IOperador proprietario, Privacidade privacidade) throws FWDocumentException;
    public void adicionarAssinatura(Assinatura assinatura);
    public String getNumero();
    public String getConteudo();
    public IOperador getProprietario();
    public Privacidade getPrivacidade();
    public Set<Assinatura> getAssinaturas();
}
