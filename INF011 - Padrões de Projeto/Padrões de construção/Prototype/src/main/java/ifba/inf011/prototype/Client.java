package ifba.inf011.prototype;

public class Client {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.prototype.classic.Main.run();

        System.out.println();

        System.out.println("=== Registry ===");
        ifba.inf011.prototype.registry.Main.run();

        /*
        Saída:

        === Classic ===
        dados originais
        42
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@55349700
        42
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@24e4522f

        === Registry ===
        Configuração-A
        100
        [tag1, tag2]
        ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1@517aae95
        */
    }
}
