package ifba.inf011.builder.withDirector;

import ifba.inf011.builder.interfaces.Builder;

public class Director {
    
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    // Permite a reutilização do Director
    public void changeBuilder(Builder builder) {
        this.builder = builder;
    }

    public void make(String type) {
        
        // Cria uma instância do produto via builder
        builder.reset(); 

        if(type.equals("default")) {
            builder.buildStepA();
            builder.buildStepB();
            builder.buildStepZ();
        }

        else {
            builder.buildStepA();
            builder.buildStepB();
            builder.buildStepZ();
            builder.buildFeatureB();
        }
    }
}
