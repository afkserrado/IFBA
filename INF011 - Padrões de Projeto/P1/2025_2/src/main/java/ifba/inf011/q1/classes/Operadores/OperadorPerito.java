package ifba.inf011.q1.classes.Operadores;

import ifba.inf011.q1.interfaces.IOperador;

public class OperadorPerito implements IOperador {
    
    public String Id;
    public String nome;

    public OperadorPerito() {
        System.out.println("Operador criado: " + this.getClass().getSimpleName());
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public String getNome() {
        return nome;
    }
}
