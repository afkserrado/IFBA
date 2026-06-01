package ifba.inf011.decorator;

// Concrete Decorator
public class SlackDecorator extends BaseNotifierDecorator {

    public SlackDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Slack: " + message);
    }
}