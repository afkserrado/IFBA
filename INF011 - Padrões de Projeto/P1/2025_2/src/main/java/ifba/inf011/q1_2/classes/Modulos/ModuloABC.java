package ifba.inf011.q1_2.classes.Modulos;

import ifba.inf011.q1_2.classes.Documentos.DocumentoCalculoPericial;
import ifba.inf011.q1_2.classes.Operadores.OperadorPerito;
import ifba.inf011.q1_2.interfaces.IModulo;

// Concrete Factory
public class ModuloABC implements IModulo {

    @Override
    public OperadorPerito criarOperador() {
        return new OperadorPerito();
    }

    @Override 
    public DocumentoCalculoPericial criarDocumento() {
        return new DocumentoCalculoPericial();
    }
}
