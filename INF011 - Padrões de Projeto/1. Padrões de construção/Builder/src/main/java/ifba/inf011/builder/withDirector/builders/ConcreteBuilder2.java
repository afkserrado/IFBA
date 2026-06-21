package ifba.inf011.builder.withDirector.builders;

import ifba.inf011.builder.Product2;
import ifba.inf011.builder.interfaces.Builder;

// Builder concreto para construção não encadeada
public class ConcreteBuilder2 implements Builder {

    private Product2 result;

    public ConcreteBuilder2() {
        this.result = new Product2();
    }

    @Override 
    public void reset() {
        this.result = new Product2();
    }

    @Override
    public void buildStepA() {
        result.setStepA(true);
    }

    @Override
    public void buildStepB() {
        result.setStepB(true);
    }

    @Override
    public void buildStepZ() {
        result.setStepZ(true);
    }

    @Override public void buildFeatureB() {
        result.setFeatureB(true);
    }

    public Product2 getResult() {
        System.out.println("Product2 criado.");
        return this.result;
    }
}
