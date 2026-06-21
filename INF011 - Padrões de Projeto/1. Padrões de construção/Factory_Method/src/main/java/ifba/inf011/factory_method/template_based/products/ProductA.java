package ifba.inf011.factory_method.template_based.products;

import ifba.inf011.factory_method.template_based.interfaces.Product;

public class ProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("TemplateBased: doSomething from ProductA");
    }
}