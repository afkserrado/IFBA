package ifba.inf011.iterator.classic.iterators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import ifba.inf011.iterator.classic.social.Facebook;
import ifba.inf011.iterator.domain.Profile;

// Concrete Iterator
public class FacebookIterator implements ProfileIterator {

    private final Facebook facebook;
    private final String profileEmail;
    private final String type;

    private int currentPosition;
    private List<String> emails;
    private List<Profile> profiles;

    public FacebookIterator(Facebook facebook, String profileEmail, String type) {
        this.facebook = facebook;
        this.profileEmail = profileEmail;
        this.type = type;
        this.currentPosition = 0;
    }

    private void lazyLoad() {
        if (this.emails != null) {
            return;
        }

        this.emails = new ArrayList<>(
            this.facebook.requestContacts(this.profileEmail, this.type)
        );

        this.profiles = new ArrayList<>(
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
        Profile profile = this.profiles.get(this.currentPosition);

        if (profile == null) {
            profile = this.facebook.requestProfile(email);
            this.profiles.set(this.currentPosition, profile);
        }

        this.currentPosition++;
        return profile;
    }

    @Override
    public void reset() {
        this.currentPosition = 0;
    }
}
