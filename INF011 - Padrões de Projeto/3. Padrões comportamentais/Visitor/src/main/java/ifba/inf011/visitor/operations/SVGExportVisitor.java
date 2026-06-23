package ifba.inf011.visitor.operations;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class SVGExportVisitor implements ShapeVisitor {

    private final StringBuilder output;

    public SVGExportVisitor() {
        this.output = new StringBuilder();
    }

    public String export(Shape... shapes) {
        output.setLength(0);
        output.append("<svg>\n");

        for (Shape shape : shapes) {
            shape.accept(this);
        }

        output.append("</svg>");
        return output.toString();
    }

    @Override
    public void visitDot(Dot dot) {
        output.append("    <circle cx=\"")
                .append(dot.getX())
                .append("\" cy=\"")
                .append(dot.getY())
                .append("\" r=\"1\" />\n");
    }

    @Override
    public void visitCircle(Circle circle) {
        output.append("    <circle cx=\"")
                .append(circle.getX())
                .append("\" cy=\"")
                .append(circle.getY())
                .append("\" r=\"")
                .append(circle.getRadius())
                .append("\" />\n");
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        output.append("    <rect x=\"")
                .append(rectangle.getX())
                .append("\" y=\"")
                .append(rectangle.getY())
                .append("\" width=\"")
                .append(rectangle.getWidth())
                .append("\" height=\"")
                .append(rectangle.getHeight())
                .append("\" />\n");
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        output.append("    <g id=\"shape-")
                .append(compoundShape.getId())
                .append("\">\n");

        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }

        output.append("    </g>\n");
    }
}