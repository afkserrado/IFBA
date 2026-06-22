package ifba.inf011.memento.domain;

// Domain Object
public class CircleShape extends BaseShape {

    private final int radius;

    public CircleShape(int x, int y, int radius, String color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public Shape copy() {
        return new CircleShape(getX(), getY(), radius, getColor());
    }

    @Override
    public String describe() {
        return baseDescription("Circle") + ", radius=" + radius;
    }
}
