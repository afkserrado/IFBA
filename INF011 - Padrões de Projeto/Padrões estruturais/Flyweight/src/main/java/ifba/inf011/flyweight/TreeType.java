package ifba.inf011.flyweight;

// Concrete Flyweight
public final class TreeType implements TreeDrawable {

    private final String name;
    private final String color;
    private final String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println(
            "Árvore compartilhada [" +
            name + ", " + color + ", " + texture +
            "] em (" + x + ", " + y + ")"
        );
    }
}