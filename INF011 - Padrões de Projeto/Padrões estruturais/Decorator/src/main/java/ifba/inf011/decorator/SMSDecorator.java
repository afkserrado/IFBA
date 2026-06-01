package ifba.inf011.decorator;

// Concrete Decorator
public class SMSDecorator extends BaseNotifierDecorator {

    public SMSDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("SMS: " + message);
    }
}