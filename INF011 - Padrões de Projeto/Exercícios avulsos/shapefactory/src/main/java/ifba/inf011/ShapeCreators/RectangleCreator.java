package ifba.inf011.ShapeCreators;

import ifba.inf011.Interfaces.Shape;
import ifba.inf011.Interfaces.ShapeCreator;
import ifba.inf011.Shapes.Rectangle;

public class RectangleCreator extends ShapeCreator {
    
    private double width;
    private double height;

    public RectangleCreator (double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected Shape createShape() {
        return new Rectangle(width, height);
    }
}
