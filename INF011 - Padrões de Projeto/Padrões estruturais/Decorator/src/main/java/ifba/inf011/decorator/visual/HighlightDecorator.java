package ifba.inf011.decorator.visual;

import ifba.inf011.decorator.Notifier;

// Concrete Decorator
public class HighlightDecorator extends VisualNotifierDecorator {

    public HighlightDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send("*** " + message + " ***");
    }
}