package ifba.inf011.factorymethod;

// Orquestrador
public class Client {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.factorymethod.classic.Main.run();

        System.out.println("\n=== Parameterized ===");
        ifba.inf011.factorymethod.parameterized.Main.run();
    }
}