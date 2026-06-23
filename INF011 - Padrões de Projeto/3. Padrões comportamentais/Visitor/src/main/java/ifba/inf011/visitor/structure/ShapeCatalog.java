package ifba.inf011.visitor.structure;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Object Structure
public class ShapeCatalog {

    private final List<Shape> shapes;

    public ShapeCatalog() {
        this.shapes = new ArrayList<>();
    }

    public void add(Shape shape) {
        this.shapes.add(shape);
    }

    public void accept(ShapeVisitor visitor) {
        for (Shape shape : shapes) {
            shape.accept(visitor);
        }
    }
}