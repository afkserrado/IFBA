package ifba.inf011.composite.transparent;

// Component
public interface TransparentComponent {
    void operation();
    void add(TransparentComponent component);
    void remove(TransparentComponent component);

    // Alternativas
    // default void add(TransparentComponent component) {
    //     throw new UnsupportedOperationException("This component do no support children.");
    // }

    // default void remove(TransparentComponent component) {
    //     throw new UnsupportedOperationException("This component do no support children.");
    // }
}