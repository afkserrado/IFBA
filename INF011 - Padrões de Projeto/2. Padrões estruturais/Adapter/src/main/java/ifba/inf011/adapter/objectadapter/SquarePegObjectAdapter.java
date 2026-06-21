package ifba.inf011.adapter.objectadapter;

import ifba.inf011.adapter.RoundPeg;
import ifba.inf011.adapter.SquarePeg;

// Adapter
public class SquarePegObjectAdapter extends RoundPeg {

    private final SquarePeg peg;

    public SquarePegObjectAdapter(SquarePeg peg) {
        this.peg = peg;
    }

    @Override
    public double getRadius() {
        return Math.sqrt(
                Math.pow(peg.getWidth() / 2, 2) * 2
        );
    }
}