package ifba.inf011.iterator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Element
public class Profile {

    private final String email;
    private final String name;
    private final Map<String, List<String>> contacts;

    public Profile(String email, String name, String... contacts) {
        this.email = email;
        this.name = name;
        this.contacts = new HashMap<>();

        for (String contact : contacts) {
            String[] parts = contact.split(":", 2);

            String type;
            String contactEmail;

            if (parts.length == 1) {
                type = "friends";
                contactEmail = parts[0];
            } else {
                type = parts[0];
                contactEmail = parts[1];
            }

            this.contacts
                .computeIfAbsent(type, key -> new ArrayList<>())
                .add(contactEmail);
        }
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getContacts(String type) {
        return Collections.unmodifiableList(
            this.contacts.getOrDefault(type, Collections.emptyList())
        );
    }

    @Override
    public String toString() {
        return this.name + " <" + this.email + ">";
    }
}
