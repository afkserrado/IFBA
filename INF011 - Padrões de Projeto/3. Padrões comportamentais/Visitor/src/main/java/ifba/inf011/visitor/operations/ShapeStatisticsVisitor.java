package ifba.inf011.visitor.operations;

import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.shapes.ShapeVisitor;

// Concrete Visitor
public class ShapeStatisticsVisitor implements ShapeVisitor {

    private int dotCount;
    private int circleCount;
    private int rectangleCount;
    private int compoundCount;

    public void reset() {
        this.dotCount = 0;
        this.circleCount = 0;
        this.rectangleCount = 0;
        this.compoundCount = 0;
    }

    @Override
    public void visitDot(Dot dot) {
        this.dotCount++;
    }

    @Override
    public void visitCircle(Circle circle) {
        this.circleCount++;
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        this.rectangleCount++;
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        this.compoundCount++;

        for (Shape child : compoundShape.getChildren()) {
            child.accept(this);
        }
    }

    public String report() {
        return "Estatísticas:\n"
                + "  Pontos: " + dotCount + "\n"
                + "  Círculos: " + circleCount + "\n"
                + "  Retângulos: " + rectangleCount + "\n"
                + "  Formas compostas: " + compoundCount;
    }
}