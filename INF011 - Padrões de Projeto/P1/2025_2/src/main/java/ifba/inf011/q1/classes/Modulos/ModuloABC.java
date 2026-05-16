package ifba.inf011.q1.classes.Modulos;

import ifba.inf011.q1.classes.Documentos.DocumentoCalculoPericial;
import ifba.inf011.q1.classes.Operadores.OperadorPerito;
import ifba.inf011.q1.interfaces.IDocumento;
import ifba.inf011.q1.interfaces.IModulo;
import ifba.inf011.q1.interfaces.IOperador;

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
