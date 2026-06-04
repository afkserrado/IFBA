package ifba.inf011.composite.safe;

// Leaf
public class SafeLeaf extends AbstractComponent {

    private final String name;

    public SafeLeaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Executando folha: " + name);

        if (getParent() instanceof Cacheable cacheable) {
            cacheable.invalidateCache();
        }
    }
}