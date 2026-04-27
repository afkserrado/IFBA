package ifba.inf011.classic.products;

import ifba.inf011.classic.interfaces.IProduct;

public class ProductB implements IProduct {
    @Override
    public void doSomething() {
        System.out.println("doSomething method from ProductB class");
    }
}
