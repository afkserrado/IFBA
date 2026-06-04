package ifba.inf011.composite;

import ifba.inf011.composite.aggregate.Cabinet;
import ifba.inf011.composite.aggregate.Chassis;
import ifba.inf011.composite.aggregate.Disk;
import ifba.inf011.composite.safe.SafeComposite;
import ifba.inf011.composite.safe.SafeLeaf;
import ifba.inf011.composite.transparent.TransparentComposite;
import ifba.inf011.composite.transparent.TransparentLeaf;

// Client
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Composite Transparente ===");

        TransparentComposite transparentRoot = new TransparentComposite("Root");
        transparentRoot.add(new TransparentLeaf("A"));
        transparentRoot.add(new TransparentLeaf("B"));
        transparentRoot.operation();

        System.out.println("\n=== Composite Seguro ===");

        SafeComposite safeRoot = new SafeComposite("Root");
        safeRoot.add(new SafeLeaf("X"));
        safeRoot.add(new SafeLeaf("Y"));
        safeRoot.operation();
        System.out.println("Quantidade de filhos: " + safeRoot.size());

        safeRoot.add(new SafeLeaf("Z"));
        safeRoot.operation();
        System.out.println("Quantidade de filhos: " + safeRoot.size());

        System.out.println("\n=== Composite Agregador ===");

        Cabinet cabinet = new Cabinet("Cabinet");
        cabinet.add(new Disk("Disk 1"));
        cabinet.add(new Disk("Disk 2"));
        System.out.println("Potência total: " + cabinet.power());
        System.out.println("Preço total: " + cabinet.netPrice());

        System.out.println("\n=== Composite Agregador Hierárquico ===");

        Chassis chassis = new Chassis("Main Chassis");
        chassis.add(new Disk("Disk A"));
        chassis.add(new Disk("Disk B"));
        cabinet.add(chassis);
        System.out.println("Potência total da árvore: " + cabinet.power());
        System.out.println("Preço total da árvore: " + cabinet.netPrice());
    }
}