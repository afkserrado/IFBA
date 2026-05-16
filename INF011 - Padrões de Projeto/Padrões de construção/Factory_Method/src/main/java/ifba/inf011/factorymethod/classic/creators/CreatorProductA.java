package ifba.inf011.factorymethod.classic.creators;

import ifba.inf011.factorymethod.classic.interfaces.Creator;
import ifba.inf011.factorymethod.classic.interfaces.IProduct;
import ifba.inf011.factorymethod.classic.products.ProductA;

public class CreatorProductA extends Creator {
    @Override
    public IProduct create() {
        return new ProductA();
    }
}
