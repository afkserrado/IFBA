package ifba.inf011.q1_2.interfaces;

// Subclass Prototype
public interface IDocumentoPrototipavel extends IDocumento, IPrototipo {
    // Retorno covariante
    @Override
    IDocumentoPrototipavel clone();
}