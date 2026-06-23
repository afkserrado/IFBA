package ifba.inf011.visitor.partial;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Shape;

// Concrete Visitor
public class CircleOnlyReportVisitor extends ShapeVisitorAdapter {

    private final StringBuilder report;

    public CircleOnlyReportVisitor() {
        this.report = new StringBuilder();
    }

    @Override
    public void visitCircle(Circle circle) {
        report.append("Círculo encontrado: id=")
                .append(circle.getId())
                .append(", raio=")
                .append(circle.getRadius())
                .append("\n");
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }
    }

    public String getReport() {
        return report.toString();
    }
}