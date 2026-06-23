package ifba.inf011.visitor.structure;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class MoveShapeVisitor implements ShapeVisitor {

    private final int dx;
    private final int dy;

    public MoveShapeVisitor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void visitDot(Dot dot) {
        dot.move(dx, dy);
    }

    @Override
    public void visitCircle(Circle circle) {
        circle.move(dx, dy);
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        rectangle.move(dx, dy);
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        compoundShape.move(dx, dy);
    }
}