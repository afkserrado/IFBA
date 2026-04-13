package ifba.inf011.Shapes;

import ifba.inf011.Interfaces.Shape;

public class Rectangle implements Shape {

    private double width;
    private double height;

    public Rectangle (double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void describe() {
        System.out.printf("Rectangle with area: %.2f%n", area());
    }
}
