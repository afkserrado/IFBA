package ifba.inf011.composite.aggregate;

// Leaf
public class Disk extends Equipment {

    public Disk(String name) {
        super(name);
    }

    @Override
    public int power() {
        return 10;
    }

    @Override
    public int netPrice() {
        return 100;
    }
}