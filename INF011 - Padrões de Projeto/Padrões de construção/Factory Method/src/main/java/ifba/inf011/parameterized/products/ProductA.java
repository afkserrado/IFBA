package ifba.inf011.parameterized.products;

import ifba.inf011.parameterized.interfaces.IProduct;

public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("Parameterized: doSomething method from ProductA class");
    }
}
