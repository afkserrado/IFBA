package ifba.inf011.visitor.structure;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class PrintShapeVisitor implements ShapeVisitor {

    private final StringBuilder output;

    public PrintShapeVisitor() {
        this.output = new StringBuilder();
    }

    public void reset() {
        output.setLength(0);
    }

    @Override
    public void visitDot(Dot dot) {
        output.append("Dot: id=")
                .append(dot.getId())
                .append(", x=")
                .append(dot.getX())
                .append(", y=")
                .append(dot.getY())
                .append("\n");
    }

    @Override
    public void visitCircle(Circle circle) {
        output.append("Circle: id=")
                .append(circle.getId())
                .append(", x=")
                .append(circle.getX())
                .append(", y=")
                .append(circle.getY())
                .append(", radius=")
                .append(circle.getRadius())
                .append("\n");
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        output.append("Rectangle: id=")
                .append(rectangle.getId())
                .append(", x=")
                .append(rectangle.getX())
                .append(", y=")
                .append(rectangle.getY())
                .append(", width=")
                .append(rectangle.getWidth())
                .append(", height=")
                .append(rectangle.getHeight())
                .append("\n");
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        output.append("CompoundShape: id=")
                .append(compoundShape.getId())
                .append("\n");

        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }
    }

    public String getOutput() {
        return output.toString();
    }
}