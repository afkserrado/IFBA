package ifba.inf011.iterator.gof;

import java.util.NoSuchElementException;

import ifba.inf011.iterator.domain.Profile;

// Concrete Iterator
public class ContactTypeProfileIterator implements GofIterator<Profile> {

    private final ProfileList profiles;
    private final String type;
    private int position;

    public ContactTypeProfileIterator(ProfileList profiles, String type) {
        this.profiles = profiles;
        this.type = type;
        this.first();
    }

    @Override
    public void first() {
        this.position = 0;
        this.advanceToValidProfile();
    }

    @Override
    public void next() {
        this.position++;
        this.advanceToValidProfile();
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

    private void advanceToValidProfile() {
        while (!this.isDone() && !this.hasContactType(this.profiles.get(this.position))) {
            this.position++;
        }
    }

    private boolean hasContactType(Profile profile) {
        return !profile.getContacts(this.type).isEmpty();
    }
}
