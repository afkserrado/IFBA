package ifba.inf011.iterator.gof;

// Iterator
public interface GofIterator<T> {
    void first();
    void next();
    boolean isDone();
    T currentItem();
}
