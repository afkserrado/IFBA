package ifba.inf011.q1_2.classes.Operadores;

import ifba.inf011.q1_2.interfaces.IOperadorPrototipavel;

// Adaptado para atender a questão 2

// Concrete Product
// Concrete Prototype
public class OperadorCalculista implements IOperadorPrototipavel {
    
    private String Id;
    private String nome;

    public OperadorCalculista() {
        System.out.println("Operador criado: " + this.getClass().getSimpleName());
    }

    // Construtor sobrecarregado
    private OperadorCalculista(OperadorCalculista prototipo) {
        this.Id = prototipo.Id;
        this.nome = prototipo.nome;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    // Retorno covariante
    public IOperadorPrototipavel clone() {
        return new OperadorCalculista(this);
    }
}
