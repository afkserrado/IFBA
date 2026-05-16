package ifba.inf011.factory_method.extended_parameterized.products;

import ifba.inf011.factory_method.extended_parameterized.interfaces.Product;

public class ProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("ExtendedParameterized: doSomething from ProductA");
    }
}