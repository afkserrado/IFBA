package ifba.inf011.abstract_factory.concrete_factories;

import ifba.inf011.abstract_factory.concrete_products.ProductA1;
import ifba.inf011.abstract_factory.concrete_products.ProductB1;
import ifba.inf011.abstract_factory.interfaces.AbstractFactory;
import ifba.inf011.abstract_factory.interfaces.AbstractProductA;
import ifba.inf011.abstract_factory.interfaces.AbstractProductB;

// Cria apenas produtos da família 1
// Exemplo: Modern Chair e Modern Sofa
public class ConcreteFactory1 implements AbstractFactory {

    @Override
    public AbstractProductA createProductA() {
        return new ProductA1(); // Exemplo: Modern Chair
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1(); // Exemplo: Modern Sofa
    }
}