package ifba.inf011.abstract_factory.concrete_factories;

import ifba.inf011.abstract_factory.concrete_products.ProductA2;
import ifba.inf011.abstract_factory.concrete_products.ProductB2;
import ifba.inf011.abstract_factory.interfaces.AbstractFactory;
import ifba.inf011.abstract_factory.interfaces.AbstractProductA;
import ifba.inf011.abstract_factory.interfaces.AbstractProductB;

// Cria apenas produtos da família 2
public class ConcreteFactory2 implements AbstractFactory {

    @Override
    public AbstractProductA createProductA() {
        return new ProductA2(); // Exemplo: Victorian Chair
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2(); // Victorian Sofa
    }
}