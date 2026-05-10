package ifba.inf011;

// Orquestrador
public class App {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.classic.Main.run();

        System.out.println("\n=== Parameterized ===");
        ifba.inf011.parameterized.Main.run();
    }
}