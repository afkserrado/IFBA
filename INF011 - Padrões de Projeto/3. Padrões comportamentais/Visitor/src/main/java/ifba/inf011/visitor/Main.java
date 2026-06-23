package ifba.inf011.visitor;

import ifba.inf011.visitor.classic.XMLExportVisitor;
import ifba.inf011.visitor.operations.AreaCalculatorVisitor;
import ifba.inf011.visitor.operations.SVGExportVisitor;
import ifba.inf011.visitor.operations.ShapeStatisticsVisitor;
import ifba.inf011.visitor.partial.CircleOnlyReportVisitor;
import ifba.inf011.visitor.shapes.Circle;
import ifba.inf011.visitor.shapes.CompoundShape;
import ifba.inf011.visitor.shapes.Dot;
import ifba.inf011.visitor.shapes.Rectangle;
import ifba.inf011.visitor.shapes.Shape;
import ifba.inf011.visitor.structure.MoveShapeVisitor;
import ifba.inf011.visitor.structure.PrintShapeVisitor;
import ifba.inf011.visitor.structure.ShapeCatalog;

// Client
public class Main {

    public static void main(String[] args) {

        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(dot);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape nestedCompoundShape = new CompoundShape(5);
        nestedCompoundShape.add(new Dot(6, 100, 100));
        compoundShape.add(nestedCompoundShape);

        System.out.println("=== Implementação 1: Visitor canônico com XML ===");

        XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();
        System.out.println(xmlExportVisitor.export(circle, compoundShape));

        System.out.println("\n=== Implementação 2: Múltiplas operações e estado acumulado ===");

        Shape[] shapes = new Shape[] {
                dot,
                circle,
                rectangle,
                compoundShape
        };

        AreaCalculatorVisitor areaCalculatorVisitor = new AreaCalculatorVisitor();

        for (Shape shape : shapes) {
            shape.accept(areaCalculatorVisitor);
        }

        System.out.println("Área total: " + areaCalculatorVisitor.getTotalArea());

        SVGExportVisitor svgExportVisitor = new SVGExportVisitor();
        System.out.println(svgExportVisitor.export(shapes));

        ShapeStatisticsVisitor statisticsVisitor = new ShapeStatisticsVisitor();

        for (Shape shape : shapes) {
            shape.accept(statisticsVisitor);
        }

        System.out.println(statisticsVisitor.report());

        System.out.println("\n=== Implementação 3: Visitor parcial com métodos padrão ===");

        CircleOnlyReportVisitor circleOnlyReportVisitor = new CircleOnlyReportVisitor();

        for (Shape shape : shapes) {
            shape.accept(circleOnlyReportVisitor);
        }

        System.out.println(circleOnlyReportVisitor.getReport());

        System.out.println("\n=== Implementação 4: Object Structure aplicando visitors ===");

        ShapeCatalog catalog = new ShapeCatalog();
        catalog.add(new Dot(7, 0, 0));
        catalog.add(new Circle(8, 5, 5, 3));
        catalog.add(new Rectangle(9, 10, 10, 15, 8));
        catalog.add(compoundShape);

        PrintShapeVisitor printVisitor = new PrintShapeVisitor();

        catalog.accept(printVisitor);
        System.out.println(printVisitor.getOutput());

        MoveShapeVisitor moveVisitor = new MoveShapeVisitor(10, 20);
        catalog.accept(moveVisitor);

        printVisitor.reset();
        catalog.accept(printVisitor);

        System.out.println("Após mover:");
        System.out.println(printVisitor.getOutput());
    }
}