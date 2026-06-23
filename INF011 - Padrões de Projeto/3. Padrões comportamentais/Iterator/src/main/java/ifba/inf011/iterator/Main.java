package ifba.inf011.iterator;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.iterator.classic.client.SocialMessenger;
import ifba.inf011.iterator.classic.social.Facebook;
import ifba.inf011.iterator.classic.social.LinkedIn;
import ifba.inf011.iterator.classic.social.SocialNetwork;
import ifba.inf011.iterator.cursor.CursorProfileList;
import ifba.inf011.iterator.cursor.ProfileCursor;
import ifba.inf011.iterator.domain.Profile;
import ifba.inf011.iterator.gof.GofIterator;
import ifba.inf011.iterator.gof.ProfileList;
import ifba.inf011.iterator.internal.CoworkerProfilePrinter;
import ifba.inf011.iterator.internal.FirstNProfilesPrinter;

// Client
public class Main {

    public static void main(String[] args) {

        List<Profile> profiles = createProfiles();

        System.out.println("=== Implementação 1: Iterator canônico com rede social ===");

        SocialNetwork facebook = new Facebook(profiles);
        SocialNetwork linkedIn = new LinkedIn(profiles);
        SocialMessenger messenger = new SocialMessenger();

        System.out.println("\n--- Amigos no Facebook ---");
        messenger.send(
            facebook.createFriendsIterator("anna.smith@email.com"),
            "Olá, tudo bem?"
        );

        System.out.println("\n--- Colegas no LinkedIn ---");
        messenger.send(
            linkedIn.createCoworkersIterator("anna.smith@email.com"),
            "Mensagem profissional."
        );

        System.out.println("\n=== Implementação 2: Iteradores externos ===");

        ProfileList profileList = new ProfileList();

        for (Profile profile : profiles) {
            profileList.add(profile);
        }

        print("Ordem normal", profileList.createIterator());
        print("Ordem reversa", profileList.createReverseIterator());
        print("Ordem aleatória", profileList.createRandomIterator());
        print("Perfis com amigos", profileList.createContactTypeIterator("friends"));
        print("Null Iterator", profileList.createEmptyIterator());

        System.out.println("\n=== Implementação 3: Cursor ===");

        CursorProfileList cursorList = new CursorProfileList(profiles);
        ProfileCursor cursor = cursorList.createCursor();

        for (
            cursorList.first(cursor);
            !cursorList.isDone(cursor);
            cursorList.next(cursor)
        ) {
            System.out.println(cursorList.currentItem(cursor));
        }

        System.out.println("\n=== Implementação 4: Iterator interno ===");

        System.out.println("\n--- Primeiros 2 perfis ---");
        FirstNProfilesPrinter firstTwo = new FirstNProfilesPrinter(
            profileList.createIterator(),
            2
        );
        firstTwo.traverse();

        System.out.println("\n--- Perfis com colegas ---");
        CoworkerProfilePrinter coworkerPrinter = new CoworkerProfilePrinter(
            profileList.createIterator()
        );
        coworkerPrinter.traverse();
    }

    private static void print(String title, GofIterator<Profile> iterator) {
        System.out.println("\n--- " + title + " ---");

        for (
            iterator.first();
            !iterator.isDone();
            iterator.next()
        ) {
            System.out.println(iterator.currentItem());
        }
    }

    private static List<Profile> createProfiles() {
        List<Profile> profiles = new ArrayList<>();

        profiles.add(
            new Profile(
                "anna.smith@email.com",
                "Anna Smith",
                "friends:max@email.com",
                "friends:liza@email.com",
                "coworkers:sam@email.com"
            )
        );

        profiles.add(
            new Profile(
                "max@email.com",
                "Max",
                "friends:anna.smith@email.com",
                "coworkers:sam@email.com"
            )
        );

        profiles.add(
            new Profile(
                "sam@email.com",
                "Sam",
                "coworkers:anna.smith@email.com",
                "coworkers:max@email.com"
            )
        );

        profiles.add(
            new Profile(
                "liza@email.com",
                "Liza",
                "friends:anna.smith@email.com"
            )
        );

        return profiles;
    }
}
