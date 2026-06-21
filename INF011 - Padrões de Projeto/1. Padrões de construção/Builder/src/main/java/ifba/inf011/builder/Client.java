package ifba.inf011.builder;

import ifba.inf011.builder.withDirector.Director;
import ifba.inf011.builder.withDirector.builders.ConcreteBuilder1;
import ifba.inf011.builder.withDirector.builders.ConcreteBuilder2;
import ifba.inf011.builder.withoutDirector.builders.ConcreteBuilder3;

public class Client {
    public static void main(String[] args) {
        
        // Builder with Director
        System.out.println("=== Builder with Director ===");
        ConcreteBuilder1 builder1 = new ConcreteBuilder1();
        Director director = new Director(builder1);

        director.make("default");
        Product1 p1 = builder1.getResult();
        System.out.println(p1);

        ConcreteBuilder2 builder2 = new ConcreteBuilder2();
        director.changeBuilder(builder2);

        director.make("full");
        Product2 p2 = builder2.getResult();
        System.out.println(p2);

        System.out.println();

        // Builder without Director
        System.out.println("=== Builder without Director ===");
        ConcreteBuilder3 builder3 = new ConcreteBuilder3();
        Product3 p3 = builder3
                        .reset()
                        .buildStepA()
                        .buildStepZ()
                        .buildFeatureB()
                        .getResult();

        System.out.println(p3);
    }
}
