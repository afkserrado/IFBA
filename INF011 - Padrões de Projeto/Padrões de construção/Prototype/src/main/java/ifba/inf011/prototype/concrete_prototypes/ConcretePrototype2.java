package ifba.inf011.prototype.concrete_prototypes;

import ifba.inf011.prototype.interfaces.Prototype;

public class ConcretePrototype2 implements Prototype {

    private int value;

    public ConcretePrototype2(int value) {
        this.value = value;
    }

    // Construtor sobrecarregado
    public ConcretePrototype2(ConcretePrototype2 source) {
        this.value = source.value;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype2(this);
    }

    public int getValue() {
        return value;
    }
}
