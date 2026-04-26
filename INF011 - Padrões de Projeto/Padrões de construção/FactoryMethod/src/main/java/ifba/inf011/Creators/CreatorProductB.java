package ifba.inf011.Creators;

import ifba.inf011.Products.ProductB;
import ifba.inf011.interfaces.Creator;
import ifba.inf011.interfaces.IProduct;

public class CreatorProductB extends Creator {
    @Override
    public IProduct create() {
        return new ProductB();
    }
}
