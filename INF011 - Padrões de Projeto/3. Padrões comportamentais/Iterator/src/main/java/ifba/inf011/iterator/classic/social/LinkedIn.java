package ifba.inf011.iterator.classic.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifba.inf011.iterator.classic.iterators.LinkedInIterator;
import ifba.inf011.iterator.classic.iterators.ProfileIterator;
import ifba.inf011.iterator.domain.Profile;

// Concrete Aggregate
public class LinkedIn implements SocialNetwork {

    private final List<Profile> contacts;

    public LinkedIn(List<Profile> contacts) {
        this.contacts = new ArrayList<>(contacts);
    }

    public Profile requestContactInfo(String profileEmail) {
        System.out.println("LinkedIn: carregando perfil " + profileEmail);
        return this.findProfile(profileEmail);
    }

    public List<String> requestRelatedContacts(String profileEmail, String type) {
        System.out.println("LinkedIn: carregando contatos do tipo " + type + " de " + profileEmail);

        Profile profile = this.findProfile(profileEmail);

        if (profile == null) {
            return Collections.emptyList();
        }

        return profile.getContacts(type);
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : this.contacts) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }

        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new LinkedInIterator(this, profileEmail, "friends");
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new LinkedInIterator(this, profileEmail, "coworkers");
    }
}
