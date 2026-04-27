package ifba.inf011.classic.creators;

import ifba.inf011.classic.interfaces.Creator;
import ifba.inf011.classic.interfaces.IProduct;
import ifba.inf011.classic.products.ProductA;

public class CreatorProductA extends Creator {
    @Override
    public IProduct create() {
        return new ProductA();
    }
}
