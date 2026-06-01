package ifba.inf011.decorator.visual;

import ifba.inf011.decorator.Notifier;

// Decorator
public abstract class VisualNotifierDecorator implements Notifier {

    protected Notifier notifier;

    public VisualNotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        notifier.send(message);
    }
}