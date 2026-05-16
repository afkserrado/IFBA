package ifba.inf011.prototype.concrete_prototypes;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.prototype.interfaces.Prototype;

public class ConcretePrototype2 implements Prototype {

    private int value;
    private List<String> tags; // Campo que é referência
    private ConcretePrototype1 nested; // Campo é um outro prototype

    public ConcretePrototype2(int value, List<String> tags, ConcretePrototype1 nested) {
        this.value = value;
        this.tags = tags;
        this.nested = nested;
    }

    // Construtor sobrecarregado
    // Deep copy
    private ConcretePrototype2(ConcretePrototype2 source) {
        this.value = source.value;
        this.tags = new ArrayList<>(source.tags); // cópia profunda da lista
        this.nested = (ConcretePrototype1) source.nested.clone(); // cópia profunda
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype2(this);
    }

    public int getValue() {
        return value;
    }

    public List<String> getTags() {
        return tags;
    }

    public ConcretePrototype1 getNested() {
        return nested;
    }
}
