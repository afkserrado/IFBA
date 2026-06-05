package ifba.inf011.flyweight;

// Unshared Concrete Flyweight
public final class LegendaryTree implements TreeDrawable {

    private final String name;
    private final String power;

    public LegendaryTree(String name, String power) {
        this.name = name;
        this.power = power;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println(
            "Árvore lendária [" +
            name + ", poder=" + power +
            "] em (" + x + ", " + y + ")"
        );
    }
}