package ifba.inf011.memento.classic;

import java.util.Stack;

// Caretaker
public class EditorHistory {

    private final Stack<EditorMemento> history;

    public EditorHistory() {
        this.history = new Stack<>();
    }

    public void save(GraphicEditor editor, String name) {
        EditorMemento memento = editor.save(name);
        this.history.push(memento);
        System.out.println("Histórico: snapshot salvo -> " + memento.getName());
    }

    public void undo(GraphicEditor editor) {
        if (history.isEmpty()) {
            System.out.println("Histórico: nada para desfazer.");
            return;
        }

        EditorMemento memento = history.pop();
        editor.restore(memento);
        System.out.println("Histórico: restaurado -> " + memento.getName());
    }
}
