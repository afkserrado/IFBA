package ifba.inf011.iterator.gof;

import ifba.inf011.iterator.domain.Profile;

// Aggregate
public interface ProfileAggregate {
    GofIterator<Profile> createIterator();
    GofIterator<Profile> createReverseIterator();
    GofIterator<Profile> createRandomIterator();
    GofIterator<Profile> createContactTypeIterator(String type);
    GofIterator<Profile> createEmptyIterator();
}
