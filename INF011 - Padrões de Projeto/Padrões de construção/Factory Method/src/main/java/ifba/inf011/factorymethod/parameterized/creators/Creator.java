package ifba.inf011.factorymethod.parameterized.creators;

import ifba.inf011.factorymethod.parameterized.enums.ProductId;
import ifba.inf011.factorymethod.parameterized.interfaces.IProduct;
import ifba.inf011.factorymethod.parameterized.products.*;

public class Creator {
    public IProduct create(ProductId id) {
        if(id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return switch(id) {
            case A -> new ProductA();
            case B -> new ProductB();
        };
    }

    public void anOperation(ProductId id) {
        IProduct product = create(id);
        product.doSomething();
    }
}
