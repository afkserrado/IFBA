package ifba.inf011.iterator.internal;

import ifba.inf011.iterator.domain.Profile;
import ifba.inf011.iterator.gof.GofIterator;

// Concrete Internal Iterator
public class FirstNProfilesPrinter extends ProfileTraverser {

    private final int limit;
    private int count;

    public FirstNProfilesPrinter(GofIterator<Profile> iterator, int limit) {
        super(iterator);
        this.limit = limit;
        this.count = 0;
    }

    @Override
    protected boolean processItem(Profile profile) {
        this.count++;
        System.out.println(profile);
        return this.count < this.limit;
    }
}
