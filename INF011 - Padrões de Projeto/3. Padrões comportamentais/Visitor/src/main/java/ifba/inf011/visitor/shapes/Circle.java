package ifba.inf011.visitor.shapes;

// Concrete Element
public class Circle extends Dot {

    private final int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Desenhando círculo " + getId() + ".");
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }
}