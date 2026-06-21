package ifba.inf011.builder.withoutDirector.builders;

import ifba.inf011.builder.Product3;
import ifba.inf011.builder.interfaces.FluentBuilder;

// Builder concreto para construção encadeada
public class ConcreteBuilder3 implements FluentBuilder {
        
    private Product3 result;

    public ConcreteBuilder3() {
        this.result = new Product3();
    }

    @Override 
    public ConcreteBuilder3 reset() {
        this.result = new Product3();
        return this;
    }

    @Override
    public ConcreteBuilder3 buildStepA() {
        result.setStepA(true);
        return this;
    }

    @Override
    public ConcreteBuilder3 buildStepB() {
        // Product3 não possui o atributo 'stepB'
        // É comum implementar métodos vazios no Builder
        return this;
    }

    @Override
    public ConcreteBuilder3 buildStepZ() {
        result.setStepZ(true);
        return this;
    }

    @Override 
    public ConcreteBuilder3 buildFeatureB() {
        result.setFeatureB(true);
        return this;
    }

    public Product3 getResult() {
        System.out.println("Product3 criado.");
        return this.result;
    }
}
