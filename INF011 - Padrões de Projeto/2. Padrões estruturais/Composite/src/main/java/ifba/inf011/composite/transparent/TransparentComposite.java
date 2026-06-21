package ifba.inf011.composite.transparent;

import java.util.ArrayList;
import java.util.List;

// Composite
public class TransparentComposite implements TransparentComponent {

    private final String name;
    private final List<TransparentComponent> children = new ArrayList<>();

    public TransparentComposite(String name) {
        this.name = name;
    }

    @Override
    public void operation() {

        System.out.println("Composite: " + name);

        for (TransparentComponent child : children) {
            child.operation();
        }
    }

    @Override
    public void add(TransparentComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TransparentComponent component) {
        children.remove(component);
    }
}