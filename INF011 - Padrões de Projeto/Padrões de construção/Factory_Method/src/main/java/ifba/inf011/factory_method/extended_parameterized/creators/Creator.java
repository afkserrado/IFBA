package ifba.inf011.factory_method.extended_parameterized.creators;

import ifba.inf011.factory_method.extended_parameterized.enums.BasicProductType;
import ifba.inf011.factory_method.extended_parameterized.enums.ProductType;
import ifba.inf011.factory_method.extended_parameterized.interfaces.Product;
import ifba.inf011.factory_method.extended_parameterized.products.ProductA;
import ifba.inf011.factory_method.extended_parameterized.products.ProductB;

public class Creator {

    // Factory Method parametrizado com implementação padrão
    public Product create(ProductType type) {
        return switch ((BasicProductType) type) {
            case A -> new ProductA();
            case B -> new ProductB();
        };
    }

    public void anOperation(ProductType type) {
        Product product = create(type);
        product.doSomething();
    }
}