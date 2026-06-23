package ifba.inf011.iterator.classic.social;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifba.inf011.iterator.classic.iterators.FacebookIterator;
import ifba.inf011.iterator.classic.iterators.ProfileIterator;
import ifba.inf011.iterator.domain.Profile;

// Concrete Aggregate
public class Facebook implements SocialNetwork {

    private final List<Profile> profiles;

    public Facebook(List<Profile> profiles) {
        this.profiles = new ArrayList<>(profiles);
    }

    public Profile requestProfile(String profileEmail) {
        System.out.println("Facebook: carregando perfil " + profileEmail);
        return this.findProfile(profileEmail);
    }

    public List<String> requestContacts(String profileEmail, String type) {
        System.out.println("Facebook: carregando contatos do tipo " + type + " de " + profileEmail);

        Profile profile = this.findProfile(profileEmail);

        if (profile == null) {
            return Collections.emptyList();
        }

        return profile.getContacts(type);
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : this.profiles) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }

        return null;
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, profileEmail, "friends");
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, profileEmail, "coworkers");
    }
}
