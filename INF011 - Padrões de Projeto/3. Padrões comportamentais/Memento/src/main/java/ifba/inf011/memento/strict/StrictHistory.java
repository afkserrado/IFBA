package ifba.inf011.memento.strict;

import java.util.Stack;

// Caretaker
public class StrictHistory {

    private final Stack<RestorableMemento> history;

    public StrictHistory() {
        this.history = new Stack<>();
    }

    public void push(RestorableMemento memento) {
        this.history.push(memento);
        System.out.println("Snapshot estrito salvo: " + memento.getName());
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("StrictHistory: nada para desfazer.");
            return;
        }

        RestorableMemento memento = history.pop();
        memento.restore();
        System.out.println("Snapshot estrito restaurado: " + memento.getName());
    }
}
