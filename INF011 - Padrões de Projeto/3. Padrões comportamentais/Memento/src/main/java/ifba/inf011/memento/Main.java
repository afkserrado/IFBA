package ifba.inf011.memento;

import ifba.inf011.memento.classic.EditorHistory;
import ifba.inf011.memento.classic.GraphicEditor;
import ifba.inf011.memento.command.ChangeColorCommand;
import ifba.inf011.memento.command.CommandHistory;
import ifba.inf011.memento.command.MoveShapeCommand;
import ifba.inf011.memento.domain.CircleShape;
import ifba.inf011.memento.domain.RectangleShape;
import ifba.inf011.memento.named.NamedVersionHistory;
import ifba.inf011.memento.strict.StrictGraphicEditor;
import ifba.inf011.memento.strict.StrictHistory;

// Client
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Implementação 1: Memento canônico com pilha ===");

        GraphicEditor editor = new GraphicEditor();
        editor.addShape(new CircleShape(10, 10, 5, "Azul"));
        editor.addShape(new RectangleShape(30, 30, 20, 10, "Verde"));

        EditorHistory history = new EditorHistory();

        history.save(editor, "Estado inicial");
        editor.moveShape(0, 15, 0);
        editor.changeColor(1, "Vermelho");
        editor.printState();

        history.undo(editor);
        editor.printState();

        System.out.println("\n=== Implementação 2: Versões nomeadas ===");

        GraphicEditor versionedEditor = new GraphicEditor();
        versionedEditor.addShape(new CircleShape(0, 0, 10, "Preto"));

        NamedVersionHistory versions = new NamedVersionHistory();

        versions.save("original", versionedEditor);

        versionedEditor.moveShape(0, 50, 50);
        versions.save("movido", versionedEditor);

        versionedEditor.changeColor(0, "Roxo");
        versions.save("colorido", versionedEditor);

        versionedEditor.printState();
        versions.listVersions();

        versions.restore("original", versionedEditor);
        versionedEditor.printState();

        System.out.println("\n=== Implementação 3: Memento + Command ===");

        GraphicEditor commandEditor = new GraphicEditor();
        commandEditor.addShape(new RectangleShape(5, 5, 15, 15, "Cinza"));

        CommandHistory commandHistory = new CommandHistory();

        commandHistory.execute(new MoveShapeCommand(commandEditor, 0, 10, 20));
        commandHistory.execute(new ChangeColorCommand(commandEditor, 0, "Laranja"));
        commandEditor.printState();

        commandHistory.undo();
        commandEditor.printState();

        commandHistory.undo();
        commandEditor.printState();

        commandHistory.redo();
        commandEditor.printState();

        System.out.println("\n=== Implementação 4: Memento com restauração própria ===");

        StrictGraphicEditor strictEditor = new StrictGraphicEditor();
        strictEditor.addShape(new CircleShape(100, 100, 25, "Amarelo"));

        StrictHistory strictHistory = new StrictHistory();

        strictHistory.push(strictEditor.save("estado inicial estrito"));
        strictEditor.moveShape(0, -30, -30);
        strictEditor.changeColor(0, "Azul");
        strictEditor.printState();

        strictHistory.undo();
        strictEditor.printState();
    }
}
