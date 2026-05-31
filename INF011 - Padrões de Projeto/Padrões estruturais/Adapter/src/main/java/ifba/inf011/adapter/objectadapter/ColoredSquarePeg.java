package ifba.inf011.adapter.objectadapter;

import ifba.inf011.adapter.SquarePeg;

// Adaptee concreto
public class ColoredSquarePeg extends SquarePeg {

    private String color;

    public ColoredSquarePeg(double width, String color) {
        super(width);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}