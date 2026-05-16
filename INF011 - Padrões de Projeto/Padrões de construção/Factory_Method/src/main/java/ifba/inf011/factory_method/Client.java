package ifba.inf011.factory_method;

// Orquestrador
public class Client {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.factory_method.classic.Main.run();

        System.out.println("\n=== Parameterized ===");
        ifba.inf011.factory_method.parameterized.Main.run();
    }
}