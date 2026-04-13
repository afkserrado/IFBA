package ifba.inf011.Interfaces;

// Creator
public abstract class ShapeCreator {

    // Factory Method
    protected abstract Shape createShape();

    public void describe() {
        Shape shape = createShape();
        shape.describe();
    }
}
