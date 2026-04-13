package ifba.inf011;

import ifba.inf011.Interfaces.ShapeCreator;
import ifba.inf011.ShapeCreators.CircleCreator;
import ifba.inf011.ShapeCreators.RectangleCreator;
import ifba.inf011.ShapeCreators.TriangleCreator;

public class Main {
    public static void main(String[] args) {
        ShapeCreator creator;
        
        creator = new CircleCreator(5);
        creator.describe();
        
        creator = new RectangleCreator(4, 6);
        creator.describe();
        
        creator = new TriangleCreator(3, 8);
        creator.describe();
    }
}