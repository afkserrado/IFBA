package ifba.inf011.decorator;

// Concrete Decorator
public class FacebookDecorator extends BaseNotifierDecorator {

    public FacebookDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Facebook: " + message);
    }
}