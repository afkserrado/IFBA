package ifba.inf011.factory_method;

// Orquestrador
public class Client {
    public static void main(String[] args) {
        System.out.println("=== Classic ===");
        ifba.inf011.factory_method.classic.Main.run();

        System.out.println("\n=== Parameterized ===");
        ifba.inf011.factory_method.parameterized.Main.run();

        System.out.println("\n=== Extended Parameterized ===");
        ifba.inf011.factory_method.extended_parameterized.Main.run();

        System.out.println("\n=== Template Based ===");
        ifba.inf011.factory_method.template_based.Main.run();
    }
}