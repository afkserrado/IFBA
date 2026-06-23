package ifba.inf011.iterator.internal;

import ifba.inf011.iterator.domain.Profile;
import ifba.inf011.iterator.gof.GofIterator;

// Internal Iterator
public abstract class ProfileTraverser {

    private final GofIterator<Profile> iterator;

    public ProfileTraverser(GofIterator<Profile> iterator) {
        this.iterator = iterator;
    }

    public boolean traverse() {
        for (
            this.iterator.first();
            !this.iterator.isDone();
            this.iterator.next()
        ) {
            boolean shouldContinue = this.processItem(this.iterator.currentItem());

            if (!shouldContinue) {
                return false;
            }
        }

        return true;
    }

    protected abstract boolean processItem(Profile profile);
}
