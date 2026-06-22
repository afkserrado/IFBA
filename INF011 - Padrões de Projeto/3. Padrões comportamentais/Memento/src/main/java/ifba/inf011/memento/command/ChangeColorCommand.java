package ifba.inf011.memento.command;

import ifba.inf011.memento.classic.GraphicEditor;

// Concrete Command
public class ChangeColorCommand extends AbstractEditorCommand {

    private final int shapeIndex;
    private final String color;

    public ChangeColorCommand(GraphicEditor editor, int shapeIndex, String color) {
        super(editor);
        this.shapeIndex = shapeIndex;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Alterar cor da forma " + shapeIndex;
    }

    @Override
    public void execute() {
        makeBackup();
        editor.changeColor(shapeIndex, color);
        System.out.println("Executado: " + getName());
    }
}
