package ifba.inf011.Creators;

import ifba.inf011.Products.ProductA;
import ifba.inf011.interfaces.Creator;
import ifba.inf011.interfaces.IProduct;

public class CreatorProductA extends Creator {
    @Override
    public IProduct create() {
        return new ProductA();
    }
}
