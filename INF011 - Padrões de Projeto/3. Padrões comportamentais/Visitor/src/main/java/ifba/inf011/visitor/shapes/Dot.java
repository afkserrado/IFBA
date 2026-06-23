package ifba.inf011.visitor.shapes;

// Concrete Element
public class Dot implements Shape {

    private final int id;
    private int x;
    private int y;

    public Dot(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void draw() {
        System.out.println("Desenhando ponto " + id + ".");
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitDot(this);
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}