package ifba.inf011.Creators;

import ifba.inf011.Interfaces.Creator;
import ifba.inf011.Interfaces.IProduct;
import ifba.inf011.Products.ProductB;

public class CreatorProductB extends Creator {
    @Override
    public IProduct create() {
        return new ProductB();
    }
}
