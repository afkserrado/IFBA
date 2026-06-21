package ifba.inf011.factory_method.parameterized.products;

import ifba.inf011.factory_method.parameterized.interfaces.IProduct;

public class ProductA implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("Parameterized: doSomething method from ProductA class");
    }
}
