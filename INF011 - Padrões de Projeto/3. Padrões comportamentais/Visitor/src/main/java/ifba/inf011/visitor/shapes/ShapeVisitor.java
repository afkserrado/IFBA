package ifba.inf011.visitor.shapes;

// Visitor
public interface ShapeVisitor {
    void visitDot(Dot dot);
    void visitCircle(Circle circle);
    void visitRectangle(Rectangle rectangle);
    void visitCompoundShape(CompoundShape compoundShape);
}