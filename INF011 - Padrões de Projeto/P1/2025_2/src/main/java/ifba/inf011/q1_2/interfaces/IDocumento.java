package ifba.inf011.q1_2.interfaces;

import java.util.Set;

import ifba.inf011.q1_2.classes.Assinatura;
import ifba.inf011.q1_2.classes.error.FWDocumentException;
import ifba.inf011.q1_2.enums.Privacidade;

// Abstract Product
public interface IDocumento {
    public void inicializar(IOperador proprietario, Privacidade privacidade) throws FWDocumentException;
    public void adicionarAssinatura(Assinatura assinatura);
    public String getNumero();
    public String getConteudo();
    public IOperador getProprietario();
    public Privacidade getPrivacidade();
    public Set<Assinatura> getAssinaturas();
}
