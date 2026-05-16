package ifba.inf011.q1_2.classes.Modulos;

import ifba.inf011.q1_2.interfaces.IDocumento;
import ifba.inf011.q1_2.interfaces.IModulo;
import ifba.inf011.q1_2.interfaces.IOperador;
import ifba.inf011.q1_2.interfaces.IPrototipo;

public class ModuloConfiguravel implements IModulo {
    
    private IOperador operador;
    private IDocumento documento;

    public ModuloConfiguravel(IOperador operador, IDocumento documento) {
        this.operador = operador;
        this.documento = documento;
    }

    @Override
    public IOperador criarOperador() {
        return (IOperador) ((IPrototipo) operador).clone();
    }

    @Override
    public IDocumento criarDocumento() {
        return (IDocumento) ((IPrototipo) documento).clone();
    }
}
