package ifba.inf011.decorator;

// Concrete Component
public class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}