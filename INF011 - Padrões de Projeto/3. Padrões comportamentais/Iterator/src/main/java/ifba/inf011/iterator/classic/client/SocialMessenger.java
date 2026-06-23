package ifba.inf011.iterator.classic.client;

import ifba.inf011.iterator.classic.iterators.ProfileIterator;
import ifba.inf011.iterator.domain.Profile;

// Client
public class SocialMessenger {

    public void send(ProfileIterator iterator, String message) {
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            System.out.println("Mensagem enviada para " + profile.getEmail() + ": " + message);
        }
    }
}
