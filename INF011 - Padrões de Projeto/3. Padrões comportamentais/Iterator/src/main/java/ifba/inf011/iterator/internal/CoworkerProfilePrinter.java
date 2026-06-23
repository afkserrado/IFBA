package ifba.inf011.iterator.internal;

import ifba.inf011.iterator.domain.Profile;
import ifba.inf011.iterator.gof.GofIterator;

// Concrete Internal Iterator
public class CoworkerProfilePrinter extends FilteringProfileTraverser {

    public CoworkerProfilePrinter(GofIterator<Profile> iterator) {
        super(iterator);
    }

    @Override
    protected boolean testItem(Profile profile) {
        return !profile.getContacts("coworkers").isEmpty();
    }

    @Override
    protected boolean processItem(Profile profile) {
        System.out.println(profile);
        return true;
    }
}
