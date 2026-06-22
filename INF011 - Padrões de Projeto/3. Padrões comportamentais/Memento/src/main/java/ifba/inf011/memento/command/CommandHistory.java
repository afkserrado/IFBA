package ifba.inf011.memento.command;

import java.util.ArrayList;
import java.util.List;

// Caretaker
public class CommandHistory {

    private final List<EditorCommand> history;
    private int currentIndex;

    public CommandHistory() {
        this.history = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void execute(EditorCommand command) {
        while (history.size() > currentIndex) {
            history.remove(history.size() - 1);
        }

        command.execute();
        history.add(command);
        currentIndex = history.size();
    }

    public void undo() {
        if (currentIndex == 0) {
            System.out.println("CommandHistory: nada para desfazer.");
            return;
        }

        currentIndex--;
        history.get(currentIndex).undo();
    }

    public void redo() {
        if (currentIndex >= history.size()) {
            System.out.println("CommandHistory: nada para refazer.");
            return;
        }

        EditorCommand command = history.get(currentIndex);
        command.execute();
        currentIndex++;
        System.out.println("Redo: " + command.getName());
    }
}
