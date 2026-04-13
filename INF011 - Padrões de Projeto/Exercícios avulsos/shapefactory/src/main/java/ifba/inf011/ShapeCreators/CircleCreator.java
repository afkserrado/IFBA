package ifba.inf011.ShapeCreators;

import ifba.inf011.Interfaces.Shape;
import ifba.inf011.Interfaces.ShapeCreator;
import ifba.inf011.Shapes.Circle;

public class CircleCreator extends ShapeCreator {
    
    private double radius;

    public CircleCreator(double radius) {
        this.radius = radius;
    }

    @Override
    protected Shape createShape() {
        return new Circle(radius);
    }
}
