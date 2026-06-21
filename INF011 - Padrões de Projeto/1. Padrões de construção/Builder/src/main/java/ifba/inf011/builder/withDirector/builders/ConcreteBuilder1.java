package ifba.inf011.builder.withDirector.builders;

import ifba.inf011.builder.Product1;
import ifba.inf011.builder.interfaces.Builder;

// Builder concreto para construção não encadeada
public class ConcreteBuilder1 implements Builder {
    
    private Product1 result;

    public ConcreteBuilder1() {
        this.result = new Product1();
    }

    @Override 
    public void reset() {
        this.result = new Product1();
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

    @Override 
    public void buildFeatureB() {
        // Product1 não possui o atributo 'featureB'
        // É comum implementar métodos vazios no Builder
    }

    public Product1 getResult() {
        System.out.println("Product1 criado.");
        return this.result;
    }
}
