package ifba.inf011.Shapes;

import ifba.inf011.Interfaces.Shape;

public class Triangle implements Shape {

    private double base;
    private double height;

    public Triangle (double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return (base * height) / 2;
    }

    @Override
    public void describe() {
        System.out.printf("Triangle with area: %.2f%n", area());
    }
}
