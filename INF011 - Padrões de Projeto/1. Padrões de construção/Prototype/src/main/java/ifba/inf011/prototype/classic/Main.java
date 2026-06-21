package ifba.inf011.prototype.classic;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1;
import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype2;
import ifba.inf011.prototype.interfaces.Prototype;

// Cria novos objetos pedindo ao prototype que clone a si mesmo
public class Main {
    
    private Prototype prototype;

    public Main (Prototype prototype) {
        this.prototype = prototype;
    } 

    public Prototype operation() {
        return prototype.clone();
    }

    public static void run() {
        Main client1 = new Main(new ConcretePrototype1("dados originais"));
        ConcretePrototype1 clone1 = (ConcretePrototype1) client1.operation();
        System.out.println(clone1.getField());

        Main client2 = new Main(
            new ConcretePrototype2(
                42,
                new ArrayList<>(List.of("tag1", "tag2")),
                clone1
            )
        );
        ConcretePrototype2 clone2 = (ConcretePrototype2) client2.operation();
        System.out.println(clone2.getValue());
        System.out.println(clone2.getTags());
        System.out.println(clone2.getNested());

        Main client3 = new Main(clone2);
        ConcretePrototype2 clone3 = (ConcretePrototype2) client3.operation();
        System.out.println(clone3.getValue());
        System.out.println(clone3.getTags());
        System.out.println(clone3.getNested());

        /*
        Saída:
        dados originais
        42
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@55349700
        42
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@24e4522f
        */
    }
}
