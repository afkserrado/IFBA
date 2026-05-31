package ifba.inf011.adapter.classadapter;

import ifba.inf011.adapter.SquarePeg;

// Adapter
public class SquarePegClassAdapter extends SquarePeg {

    public SquarePegClassAdapter(double width) {
        super(width);
    }

    public double getRadius() {
        return Math.sqrt(
                Math.pow(getWidth() / 2, 2) * 2
        );
    }
}