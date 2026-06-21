package ifba.inf011.abstract_factory.concrete_products;

import ifba.inf011.abstract_factory.interfaces.AbstractProductA;
import ifba.inf011.abstract_factory.interfaces.AbstractProductB;

// Exemplo: Victorian Sofa
// Tipo: Sofa (B)
// Família/versão: Victorian (2)
public class ProductB2 implements AbstractProductB {

    @Override
    public String operationB() {
        return "Resultado de ProductB2";
    }

    // Produtos da mesma família (2) são compatíveis entre si
    @Override
    public String collaborateWith(AbstractProductA productA) {
        return "ProductB2 colaborando com [ " + productA.operationA() + " ]";
    }
}