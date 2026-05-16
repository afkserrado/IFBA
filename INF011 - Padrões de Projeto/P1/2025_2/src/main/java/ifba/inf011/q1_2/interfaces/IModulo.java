package ifba.inf011.q1_2.interfaces;

// Abstract Factory
public interface IModulo {

    // Factory Methods
    public IOperador criarOperador();
    public IDocumento criarDocumento();
}
