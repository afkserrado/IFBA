package ifba.inf011.flyweight;

// Client
public class Main {

    public static void main(String[] args) {
        Forest forest = new Forest();

        forest.plantTree(10, 20, "Oak", "verde escuro", "casca rugosa");
        forest.plantTree(50, 80, "Pine", "verde", "textura de agulhas");
        forest.plantTree(30, 60, "Oak", "verde escuro", "casca rugosa");
        forest.plantTree(70, 40, "Birch", "verde claro", "casca branca");
        forest.plantTree(90, 10, "Pine", "verde", "textura de agulhas");
        forest.plantLegendaryTree(500, 500, "Yggdrasil", "Imortalidade");

        forest.draw();
    }
}