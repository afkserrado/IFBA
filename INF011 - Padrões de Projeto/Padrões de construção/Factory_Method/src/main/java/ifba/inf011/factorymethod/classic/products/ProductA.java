package ifba.inf011.factorymethod.classic.products;

import ifba.inf011.factorymethod.classic.interfaces.IProduct;

public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("Classic: doSomething method from ProductA class");
    }
}
