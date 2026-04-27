package ifba.inf011.Creators;

import ifba.inf011.Interfaces.Creator;
import ifba.inf011.Interfaces.IProduct;
import ifba.inf011.Products.ProductA;

public class CreatorProductA extends Creator {
    @Override
    public IProduct create() {
        return new ProductA();
    }
}
