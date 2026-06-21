package ifba.inf011.decorator.direct;

import ifba.inf011.decorator.Notifier;

// Concrete Decorator
public class SMSDirectDecorator implements Notifier {

    private final Notifier wrappee;

    public SMSDirectDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
        System.out.println("SMS: " + message);
    }
}