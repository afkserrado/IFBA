package ifba.inf011.memento.domain;

// Domain Object
public abstract class BaseShape implements Shape {

    private int x;
    private int y;
    private String color;

    protected BaseShape(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public void moveBy(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected String getColor() {
        return color;
    }

    protected String baseDescription(String type) {
        return type + "(x=" + x + ", y=" + y + ", color=" + color + ")";
    }
}
