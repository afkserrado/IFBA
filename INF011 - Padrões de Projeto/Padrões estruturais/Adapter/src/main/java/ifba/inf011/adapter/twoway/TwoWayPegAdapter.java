package ifba.inf011.adapter.twoway;

import ifba.inf011.adapter.SquarePeg;

// Adapter
public class TwoWayPegAdapter extends SquarePeg {

    public TwoWayPegAdapter(double width) {
        super(width);
    }

    public double getRadius() {
        return Math.sqrt(
                Math.pow(getWidth() / 2, 2) * 2
        );
    }
}