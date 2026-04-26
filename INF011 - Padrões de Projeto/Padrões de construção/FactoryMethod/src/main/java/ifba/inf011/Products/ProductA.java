package ifba.inf011.Products;

import ifba.inf011.interfaces.IProduct;

public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("doSomething method from ProductA class");
    }
}
