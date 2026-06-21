package ifba.inf011.composite.aggregate;

// Component
public abstract class Equipment {

    private final String name;

    protected Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int power();
    public abstract int netPrice();
}