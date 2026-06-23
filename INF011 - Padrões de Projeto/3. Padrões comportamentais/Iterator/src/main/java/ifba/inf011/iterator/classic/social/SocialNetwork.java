package ifba.inf011.iterator.classic.social;

import ifba.inf011.iterator.classic.iterators.ProfileIterator;

// Aggregate
public interface SocialNetwork {
    ProfileIterator createFriendsIterator(String profileEmail);
    ProfileIterator createCoworkersIterator(String profileEmail);
}
