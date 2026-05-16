package ifba.inf011.abstract_factory.concrete_products;

import ifba.inf011.abstract_factory.interfaces.AbstractProductA;

// Exemplo: Victorian Chair
// Tipo: Chair (A)
// Família/versão: Victorian (2)
public class ProductA2 implements AbstractProductA {

    @Override
    public String operationA() {
        return "Resultado de ProductA2";
    }
}