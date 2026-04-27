package ifba.inf011.parameterized.products;

import ifba.inf011.parameterized.interfaces.IProduct;

public class ProductB implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("Parameterized: doSomething method from ProductB class");
    }
}
