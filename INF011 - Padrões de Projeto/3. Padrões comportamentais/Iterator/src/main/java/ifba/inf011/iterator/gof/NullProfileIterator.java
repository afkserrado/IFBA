package ifba.inf011.iterator.gof;

import java.util.NoSuchElementException;

// Null Iterator
public class NullProfileIterator<T> implements GofIterator<T> {

    @Override
    public void first() {
    }

    @Override
    public void next() {
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public T currentItem() {
        throw new NoSuchElementException("Null Iterator não possui item atual.");
    }
}
