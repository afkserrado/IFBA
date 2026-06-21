package ifba.inf011.abstract_factory.concrete_products;

import ifba.inf011.abstract_factory.interfaces.AbstractProductA;
import ifba.inf011.abstract_factory.interfaces.AbstractProductB;

// Exemplo: Modern Sofa
// Tipo: Sofa (B)
// Família/versão: Modern (1)
public class ProductB1 implements AbstractProductB {

    @Override
    public String operationB() {
        return "Resultado de ProductB1";
    }

    // Produtos da mesma família (1) são compatíveis entre si
    @Override
    public String collaborateWith(AbstractProductA productA) {
        return "ProductB1 colaborando com [ " + productA.operationA() + " ]";
    }
}