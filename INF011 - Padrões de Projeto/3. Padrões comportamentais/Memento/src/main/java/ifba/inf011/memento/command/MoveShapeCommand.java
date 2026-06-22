package ifba.inf011.memento.command;

import ifba.inf011.memento.classic.GraphicEditor;

// Concrete Command
public class MoveShapeCommand extends AbstractEditorCommand {

    private final int shapeIndex;
    private final int dx;
    private final int dy;

    public MoveShapeCommand(GraphicEditor editor, int shapeIndex, int dx, int dy) {
        super(editor);
        this.shapeIndex = shapeIndex;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public String getName() {
        return "Mover forma " + shapeIndex;
    }

    @Override
    public void execute() {
        makeBackup();
        editor.moveShape(shapeIndex, dx, dy);
        System.out.println("Executado: " + getName());
    }
}
