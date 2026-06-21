package ifba.inf011.prototype.registry;

import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1;
import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype2;

public class Main {
    
    public static void run() {

        PrototypeRegistry registry = new PrototypeRegistry();

        // Registra os protótipos pré-configurados
        registry.put("Tipo-A", new ConcretePrototype1("Configuração-A"));
        
        registry.put(
            "Tipo-B",
            new ConcretePrototype2(
                100,
                java.util.List.of("tag1", "tag2"),
                (ConcretePrototype1) registry.get("Tipo-A")
            )
        );

        // Clona pelo nome
        ConcretePrototype1 clone1 = (ConcretePrototype1) registry.get("Tipo-A");
        ConcretePrototype2 clone2 = (ConcretePrototype2) registry.get("Tipo-B");

        System.out.println(clone1.getField());
        System.out.println(clone2.getValue());
        System.out.println(clone2.getTags());
        System.out.println(clone2.getNested());

        /*
        Saída:
        Configuração-A
        100
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@517aae95
        */
    }
}
