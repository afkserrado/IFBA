package ifba.inf011.visitor.shapes;

// Element
public interface Shape {
    void move(int dx, int dy);
    void draw();
    void accept(ShapeVisitor visitor);
}