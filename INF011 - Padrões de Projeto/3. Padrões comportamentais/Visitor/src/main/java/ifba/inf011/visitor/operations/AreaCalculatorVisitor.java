package ifba.inf011.visitor.operations;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class AreaCalculatorVisitor implements ShapeVisitor {

    private double totalArea;

    public AreaCalculatorVisitor() {
        this.totalArea = 0.0;
    }

    public void reset() {
        this.totalArea = 0.0;
    }

    public double getTotalArea() {
        return totalArea;
    }

    @Override
    public void visitDot(Dot dot) {
    }

    @Override
    public void visitCircle(Circle circle) {
        this.totalArea += Math.PI * circle.getRadius() * circle.getRadius();
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        this.totalArea += rectangle.getWidth() * rectangle.getHeight();
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }
    }
}