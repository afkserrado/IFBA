package ifba.inf011.classic.products;

import ifba.inf011.classic.interfaces.IProduct;

public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("doSomething method from ProductA class");
    }
}
