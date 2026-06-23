package ifba.inf011.iterator.classic.iterators;

import ifba.inf011.iterator.domain.Profile;

// Iterator
public interface ProfileIterator {
    boolean hasNext();
    Profile getNext();
    void reset();
}
