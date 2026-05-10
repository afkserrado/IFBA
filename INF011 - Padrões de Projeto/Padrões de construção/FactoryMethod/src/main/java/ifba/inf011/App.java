package ifba.inf011;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.classic.Main.main(args);

        System.out.println("\n=== Parameterized ===");
        ifba.inf011.parameterized.Main.main(args);
    }
}