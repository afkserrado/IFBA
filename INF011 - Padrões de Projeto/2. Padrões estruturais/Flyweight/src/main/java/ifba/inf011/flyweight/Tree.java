package ifba.inf011.flyweight;

// Context
public final class Tree {

    private final int x;
    private final int y;
    private final TreeDrawable type;

    public Tree(int x, int y, TreeDrawable type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}