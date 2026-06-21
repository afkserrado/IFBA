package ifba.inf011.adapter;

import ifba.inf011.adapter.classadapter.SquarePegClassAdapter;
import ifba.inf011.adapter.objectadapter.ColoredSquarePeg;
import ifba.inf011.adapter.objectadapter.SquarePegObjectAdapter;
import ifba.inf011.adapter.twoway.TwoWayPegAdapter;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("=== Object Adapter ===");

        RoundHole hole = new RoundHole(5);
        SquarePeg smallPeg = new SquarePeg(2);
        RoundPeg adaptedPeg = new SquarePegObjectAdapter(smallPeg);
        System.out.println(hole.fits(adaptedPeg));

        System.out.println("\n=== Object Adapter com Subclasse ===");

        ColoredSquarePeg coloredPeg = new ColoredSquarePeg(4,"Azul");
        RoundPeg adaptedColoredPeg = new SquarePegObjectAdapter(coloredPeg);
        System.out.println(hole.fits(adaptedColoredPeg));

        System.out.println("\n=== Class Adapter ===");

        SquarePegClassAdapter classAdapter = new SquarePegClassAdapter(2);
        System.out.println(classAdapter.getRadius());
        
        System.out.println("\n=== Two Way Adapter ===");
        
        TwoWayPegAdapter twoWay = new TwoWayPegAdapter(2);
        System.out.println(twoWay.getWidth());
        System.out.println(twoWay.getRadius());
    }
}