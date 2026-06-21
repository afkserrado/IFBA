package ifba.inf011.composite.safe;

// Base SafeComponent
public abstract class AbstractComponent implements SafeComponent {

    private SafeComposite parent;

    public SafeComposite getParent() {
        return parent;
    }

    public void setParent(SafeComposite parent) {
        this.parent = parent;
    }
}