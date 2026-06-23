package ifba.inf011.iterator.classic.iterators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import ifba.inf011.iterator.classic.social.LinkedIn;
import ifba.inf011.iterator.domain.Profile;

// Concrete Iterator
public class LinkedInIterator implements ProfileIterator {

    private final LinkedIn linkedIn;
    private final String profileEmail;
    private final String type;

    private int currentPosition;
    private List<String> emails;
    private List<Profile> contacts;

    public LinkedInIterator(LinkedIn linkedIn, String profileEmail, String type) {
        this.linkedIn = linkedIn;
        this.profileEmail = profileEmail;
        this.type = type;
        this.currentPosition = 0;
    }

    private void lazyLoad() {
        if (this.emails != null) {
            return;
        }

        this.emails = new ArrayList<>(
            this.linkedIn.requestRelatedContacts(this.profileEmail, this.type)
        );

        this.contacts = new ArrayList<>(
            Collections.nCopies(this.emails.size(), null)
        );
    }

    @Override
    public boolean hasNext() {
        this.lazyLoad();
        return this.currentPosition < this.emails.size();
    }

    @Override
    public Profile getNext() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Não há mais perfis no iterador.");
        }

        String email = this.emails.get(this.currentPosition);
        Profile profile = this.contacts.get(this.currentPosition);

        if (profile == null) {
            profile = this.linkedIn.requestContactInfo(email);
            this.contacts.set(this.currentPosition, profile);
        }

        this.currentPosition++;
        return profile;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }
}
