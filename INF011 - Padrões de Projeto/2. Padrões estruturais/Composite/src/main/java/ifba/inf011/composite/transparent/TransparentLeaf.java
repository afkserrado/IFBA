package ifba.inf011.composite.transparent;

// Leaf
public class TransparentLeaf implements TransparentComponent {

    private final String name;

    public TransparentLeaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Executando folha: " + name);
    }

    @Override
    public void add(TransparentComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TransparentComponent component) {
        throw new UnsupportedOperationException();
    }
}