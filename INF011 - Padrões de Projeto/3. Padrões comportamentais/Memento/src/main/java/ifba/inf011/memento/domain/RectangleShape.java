package ifba.inf011.memento.domain;

// Domain Object
public class RectangleShape extends BaseShape {

    private final int width;
    private final int height;

    public RectangleShape(int x, int y, int width, int height, String color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape copy() {
        return new RectangleShape(getX(), getY(), width, height, getColor());
    }

    @Override
    public String describe() {
        return baseDescription("Rectangle")
                + ", width=" + width
                + ", height=" + height;
    }
}
