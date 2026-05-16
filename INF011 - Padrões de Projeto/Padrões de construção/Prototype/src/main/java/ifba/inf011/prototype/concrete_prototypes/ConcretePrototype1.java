package ifba.inf011.prototype.concrete_prototypes;

import ifba.inf011.prototype.interfaces.Prototype;

public class ConcretePrototype1 implements Prototype {

    private String field;

    public ConcretePrototype1(String field) {
        this.field = field;
    }

    // Construtor sobrecarregado
    public ConcretePrototype1(ConcretePrototype1 source) {
        this.field = source.field;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype1(this);
    }

    public String getField() {
        return field;
    }
}
