package ifba.inf011.decorator;

import ifba.inf011.decorator.visual.BorderDecorator;
import ifba.inf011.decorator.visual.HighlightDecorator;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Decorator Clássico ===");

        Notifier notifier =
                new SlackDecorator(
                    new FacebookDecorator(
                        new SMSDecorator(
                            new EmailNotifier()
                        )
                    )
                );

        notifier.send("Servidor indisponível");

        System.out.println();

        System.out.println("=== Decorator Visual ===");

        Notifier visual =
                new BorderDecorator(
                    new HighlightDecorator(
                        new EmailNotifier()
                    )
                );

        visual.send("Backup concluído");
    }
}