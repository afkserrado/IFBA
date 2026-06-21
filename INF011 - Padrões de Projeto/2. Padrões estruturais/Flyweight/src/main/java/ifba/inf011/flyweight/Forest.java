package ifba.inf011.flyweight;

import java.util.ArrayList;
import java.util.List;

// Client
public class Forest {

    private final List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, String color, String texture) {
        TreeDrawable type = TreeFactory.getTreeType(name, color, texture);  
		trees.add(new Tree(x, y, type));
    }
    
    public void plantLegendaryTree(int x, int y, String name, String power) {  
		trees.add(new Tree(x, y, new LegendaryTree(name, power)));
    }

    public void draw() {
        for (Tree tree : trees) {
            tree.draw();
        }

        System.out.println("Árvores plantadas: " + trees.size());
        System.out.println("Tipos de árvore compartilhados: " + TreeFactory.poolSize());
    }
}