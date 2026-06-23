package ifba.inf011.visitor.classic;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class XMLExportVisitor implements ShapeVisitor {

    private final StringBuilder output;
    private int indentationLevel;

    public XMLExportVisitor() {
        this.output = new StringBuilder();
        this.indentationLevel = 0;
    }

    public String export(Shape... shapes) {
        output.setLength(0);
        indentationLevel = 0;

        output.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");

        for (Shape shape : shapes) {
            shape.accept(this);
        }

        return output.toString();
    }

    @Override
    public void visitDot(Dot dot) {
        appendLine("<dot>");
        indentationLevel++;
        appendLine("<id>" + dot.getId() + "</id>");
        appendLine("<x>" + dot.getX() + "</x>");
        appendLine("<y>" + dot.getY() + "</y>");
        indentationLevel--;
        appendLine("</dot>");
    }

    @Override
    public void visitCircle(Circle circle) {
        appendLine("<circle>");
        indentationLevel++;
        appendLine("<id>" + circle.getId() + "</id>");
        appendLine("<x>" + circle.getX() + "</x>");
        appendLine("<y>" + circle.getY() + "</y>");
        appendLine("<radius>" + circle.getRadius() + "</radius>");
        indentationLevel--;
        appendLine("</circle>");
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        appendLine("<rectangle>");
        indentationLevel++;
        appendLine("<id>" + rectangle.getId() + "</id>");
        appendLine("<x>" + rectangle.getX() + "</x>");
        appendLine("<y>" + rectangle.getY() + "</y>");
        appendLine("<width>" + rectangle.getWidth() + "</width>");
        appendLine("<height>" + rectangle.getHeight() + "</height>");
        indentationLevel--;
        appendLine("</rectangle>");
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        appendLine("<compound_shape>");
        indentationLevel++;

        appendLine("<id>" + compoundShape.getId() + "</id>");

        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }

        indentationLevel--;
        appendLine("</compound_shape>");
    }

    private void appendLine(String line) {
        for (int i = 0; i < indentationLevel; i++) {
            output.append("    ");
        }

        output.append(line).append("\n");
    }
}