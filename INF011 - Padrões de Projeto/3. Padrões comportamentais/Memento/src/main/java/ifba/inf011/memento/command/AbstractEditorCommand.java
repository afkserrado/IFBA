package ifba.inf011.memento.command;

import ifba.inf011.memento.classic.EditorMemento;
import ifba.inf011.memento.classic.GraphicEditor;

// Command / Caretaker
public abstract class AbstractEditorCommand implements EditorCommand {

    protected final GraphicEditor editor;
    private EditorMemento backup;

    protected AbstractEditorCommand(GraphicEditor editor) {
        this.editor = editor;
    }

    protected void makeBackup() {
        this.backup = editor.save("backup antes de " + getName());
    }

    @Override
    public void undo() {
        if (backup == null) {
            System.out.println("Comando sem backup: " + getName());
            return;
        }

        editor.restore(backup);
        System.out.println("Undo: " + getName());
    }
}
