package ifba.inf011.q1_2.classes.Modulos;

import ifba.inf011.q1_2.classes.Documentos.DocumentoCalculoPericial;
import ifba.inf011.q1_2.classes.Operadores.OperadorPerito;
import ifba.inf011.q1_2.interfaces.IDocumento;
import ifba.inf011.q1_2.interfaces.IModulo;
import ifba.inf011.q1_2.interfaces.IOperador;

// Concrete Factory
public class ModuloABC implements IModulo {

    @Override
    public IOperador criarOperador() {
        return new OperadorPerito();
    }

    @Override 
    public IDocumento criarDocumento() {
        return new DocumentoCalculoPericial();
    }
}
