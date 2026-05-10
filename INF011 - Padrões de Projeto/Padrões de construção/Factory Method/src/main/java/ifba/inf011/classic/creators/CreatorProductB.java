package ifba.inf011.classic.creators;

import ifba.inf011.classic.interfaces.Creator;
import ifba.inf011.classic.interfaces.IProduct;
import ifba.inf011.classic.products.ProductB;

public class CreatorProductB extends Creator {
    @Override
    public IProduct create() {
        return new ProductB();
    }
}
