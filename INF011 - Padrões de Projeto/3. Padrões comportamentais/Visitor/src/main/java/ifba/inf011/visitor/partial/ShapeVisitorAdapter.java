package ifba.inf011.visitor.partial;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Visitor with Default Operations
public abstract class ShapeVisitorAdapter implements ShapeVisitor {

    @Override
    public void visitDot(Dot dot) {
    }

    @Override
    public void visitCircle(Circle circle) {
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
    }
}