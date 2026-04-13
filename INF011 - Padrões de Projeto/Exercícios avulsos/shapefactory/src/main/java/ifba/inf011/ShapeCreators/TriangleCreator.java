package ifba.inf011.ShapeCreators;

import ifba.inf011.Interfaces.Shape;
import ifba.inf011.Interfaces.ShapeCreator;
import ifba.inf011.Shapes.Triangle;

public class TriangleCreator extends ShapeCreator {
    
    private double base;
    private double height;

    public TriangleCreator (double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    protected Shape createShape() {
        return new Triangle(base, height);
    }
}
