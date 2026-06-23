package ifba.inf011.iterator.gof;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.iterator.domain.Profile;

// Concrete Aggregate
public class ProfileList implements ProfileAggregate {

    private final List<Profile> profiles;

    public ProfileList() {
        this.profiles = new ArrayList<>();
    }

    public void add(Profile profile) {
        this.profiles.add(profile);
    }

    public Profile get(int index) {
        return this.profiles.get(index);
    }

    public int size() {
        return this.profiles.size();
    }

    @Override
    public GofIterator<Profile> createIterator() {
        return new ForwardProfileIterator(this);
    }

    @Override
    public GofIterator<Profile> createReverseIterator() {
        return new ReverseProfileIterator(this);
    }

    @Override
    public GofIterator<Profile> createRandomIterator() {
        return new RandomProfileIterator(this);
    }

    @Override
    public GofIterator<Profile> createContactTypeIterator(String type) {
        return new ContactTypeProfileIterator(this, type);
    }

    @Override
    public GofIterator<Profile> createEmptyIterator() {
        return new NullProfileIterator<>();
    }
}
