package ifba.inf011.memento.command;

// Command
public interface EditorCommand {
    String getName();
    void execute();
    void undo();
}
