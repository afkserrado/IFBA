package ifba.inf011.q1_2.classes.Modulos;

import ifba.inf011.q1_2.interfaces.IDocumentoPrototipavel;
import ifba.inf011.q1_2.interfaces.IModulo;
import ifba.inf011.q1_2.interfaces.IOperadorPrototipavel;

public class ModuloConfiguravel implements IModulo {
    
    private IOperadorPrototipavel operador;
    private IDocumentoPrototipavel documento;

    public ModuloConfiguravel(IOperadorPrototipavel operador, IDocumentoPrototipavel documento) {
        this.operador = operador;
        this.documento = documento;
    }

    @Override
    public IOperadorPrototipavel criarOperador() {
        return operador.clone();
    }

    @Override
    public IDocumentoPrototipavel criarDocumento() {
        return documento.clone();
    }
}
