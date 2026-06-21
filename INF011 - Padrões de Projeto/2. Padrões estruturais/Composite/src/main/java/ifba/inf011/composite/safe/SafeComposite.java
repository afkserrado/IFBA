package ifba.inf011.composite.safe;

import java.util.ArrayList;
import java.util.List;

// Composite
public class SafeComposite extends AbstractComponent implements Cacheable {

    private final String name;
    private final List<SafeComponent> children = new ArrayList<>();
    private Integer cachedSize;

    public SafeComposite(String name) {
        this.name = name;
    }

    public void add(SafeComponent child) {

        children.add(child);

        if (child instanceof AbstractComponent component) {
            component.setParent(this);
        }

        invalidateCache();
    }

    public void remove(SafeComponent child) {

        children.remove(child);

        if (child instanceof AbstractComponent component) {
            component.setParent(null);
        }

        invalidateCache();
    }

    public int size() {

        if (cachedSize != null) {
            return cachedSize;
        }

        cachedSize = children.size();

        return cachedSize;
    }

    @Override
    public void invalidateCache() {

        cachedSize = null;

        if (getParent() instanceof Cacheable cacheable) {
            cacheable.invalidateCache();
        }
    }

    @Override
    public void operation() {

        System.out.println("Composite: " + name);

        for (SafeComponent child : children) {
            child.operation();
        }
    }
}