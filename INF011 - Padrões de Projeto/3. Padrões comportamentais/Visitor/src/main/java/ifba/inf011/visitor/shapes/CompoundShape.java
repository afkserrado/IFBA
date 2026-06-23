package ifba.inf011.visitor.shapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Concrete Element / Object Structure
public class CompoundShape implements Shape {

    private final int id;
    private final List<Shape> children;

    public CompoundShape(int id) {
        this.id = id;
        this.children = new ArrayList<>();
    }

    public void add(Shape shape) {
        this.children.add(shape);
    }

    @Override
    public void move(int dx, int dy) {
        for (Shape child : children) {
            child.move(dx, dy);
        }
    }

    @Override
    public void draw() {
        System.out.println("Desenhando forma composta " + id + ".");
        for (Shape child : children) {
            child.draw();
        }
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitCompoundShape(this);
    }

    public int getId() {
        return id;
    }

    public List<Shape> getChildren() {
        return Collections.unmodifiableList(children);
    }
}