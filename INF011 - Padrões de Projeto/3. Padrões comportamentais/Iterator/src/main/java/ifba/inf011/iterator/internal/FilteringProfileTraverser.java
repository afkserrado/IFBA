package ifba.inf011.iterator.internal;

import ifba.inf011.iterator.domain.Profile;
import ifba.inf011.iterator.gof.GofIterator;

// Internal Iterator
public abstract class FilteringProfileTraverser {

    private final GofIterator<Profile> iterator;

    public FilteringProfileTraverser(GofIterator<Profile> iterator) {
        this.iterator = iterator;
    }

    public boolean traverse() {
        for (
            this.iterator.first();
            !this.iterator.isDone();
            this.iterator.next()
        ) {
            Profile profile = this.iterator.currentItem();

            if (this.testItem(profile)) {
                boolean shouldContinue = this.processItem(profile);

                if (!shouldContinue) {
                    return false;
                }
            }
        }

        return true;
    }

    protected abstract boolean testItem(Profile profile);

    protected abstract boolean processItem(Profile profile);
}
