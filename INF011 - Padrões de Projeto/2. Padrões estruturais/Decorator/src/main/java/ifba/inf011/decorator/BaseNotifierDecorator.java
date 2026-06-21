package ifba.inf011.decorator;

// Decorator
public abstract class BaseNotifierDecorator implements Notifier {

    protected Notifier wrappee;

    public BaseNotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
    }
}