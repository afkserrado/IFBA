package ifba.inf011.iterator.cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ifba.inf011.iterator.domain.Profile;

// Aggregate with Cursor
public class CursorProfileList {

    private final List<Profile> profiles;

    public CursorProfileList(List<Profile> profiles) {
        this.profiles = new ArrayList<>(profiles);
    }

    public ProfileCursor createCursor() {
        return new ProfileCursor();
    }

    public void first(ProfileCursor cursor) {
        cursor.setPosition(0);
    }

    public void next(ProfileCursor cursor) {
        cursor.setPosition(cursor.getPosition() + 1);
    }

    public boolean isDone(ProfileCursor cursor) {
        return cursor.getPosition() >= this.profiles.size();
    }

    public Profile currentItem(ProfileCursor cursor) {
        if (this.isDone(cursor)) {
            throw new NoSuchElementException("Cursor finalizado.");
        }

        return this.profiles.get(cursor.getPosition());
    }
}
