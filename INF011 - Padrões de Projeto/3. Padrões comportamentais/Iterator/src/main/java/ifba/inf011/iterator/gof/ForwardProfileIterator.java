package ifba.inf011.iterator.gof;

import java.util.NoSuchElementException;

import ifba.inf011.iterator.domain.Profile;

// Concrete Iterator
public class ForwardProfileIterator implements GofIterator<Profile> {

    private final ProfileList profiles;
    private int position;

    public ForwardProfileIterator(ProfileList profiles) {
        this.profiles = profiles;
        this.first();
    }

    @Override
    public void first() {
        this.position = 0;
    }

    @Override
    public void next() {
        this.position++;
    }

    @Override
    public boolean isDone() {
        return this.position >= this.profiles.size();
    }

    @Override
    public Profile currentItem() {
        if (this.isDone()) {
            throw new NoSuchElementException("Iterador finalizado.");
        }

        return this.profiles.get(this.position);
    }
}
