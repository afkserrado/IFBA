package ifba.inf011.iterator.gof;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import ifba.inf011.iterator.domain.Profile;

// Concrete Iterator
public class RandomProfileIterator implements GofIterator<Profile> {

    private final ProfileList profiles;
    private final Random random;

    private List<Integer> order;
    private int position;

    public RandomProfileIterator(ProfileList profiles) {
        this.profiles = profiles;
        this.random = new Random();
        this.first();
    }

    @Override
    public void first() {
        this.order = new ArrayList<>();

        for (int index = 0; index < this.profiles.size(); index++) {
            this.order.add(index);
        }

        Collections.shuffle(this.order, this.random);
        this.position = 0;
    }

    @Override
    public void next() {
        this.position++;
    }

    @Override
    public boolean isDone() {
        return this.position >= this.order.size();
    }

    @Override
    public Profile currentItem() {
        if (this.isDone()) {
            throw new NoSuchElementException("Iterador finalizado.");
        }

        int realIndex = this.order.get(this.position);
        return this.profiles.get(realIndex);
    }
}
