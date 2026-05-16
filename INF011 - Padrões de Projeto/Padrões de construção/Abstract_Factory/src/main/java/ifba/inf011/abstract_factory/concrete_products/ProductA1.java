package ifba.inf011.abstract_factory.concrete_products;

import ifba.inf011.abstract_factory.interfaces.AbstractProductA;

// Exemplo: Modern Chair
// Tipo: Chair (A)
// Família/versão: Modern (1)
public class ProductA1 implements AbstractProductA {

    @Override
    public String operationA() {
        return "Resultado de ProductA1";
    }
}