package ifba.inf011.decorator.visual;

import ifba.inf011.decorator.Notifier;

// Concrete Decorator
public class BorderDecorator extends VisualNotifierDecorator {

    public BorderDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(
            "====================\n" +
            message +
            "\n===================="
        );
    }
}