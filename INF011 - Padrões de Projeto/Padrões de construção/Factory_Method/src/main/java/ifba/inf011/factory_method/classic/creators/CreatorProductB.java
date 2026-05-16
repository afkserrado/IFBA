package ifba.inf011.factory_method.classic.creators;

import ifba.inf011.factory_method.classic.interfaces.Creator;
import ifba.inf011.factory_method.classic.interfaces.IProduct;
import ifba.inf011.factory_method.classic.products.ProductB;

public class CreatorProductB extends Creator {
    @Override
    public IProduct create() {
        return new ProductB();
    }
}
