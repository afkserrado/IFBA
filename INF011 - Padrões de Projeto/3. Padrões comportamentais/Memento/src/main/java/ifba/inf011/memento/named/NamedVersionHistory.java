package ifba.inf011.memento.named;

import java.util.LinkedHashMap;
import java.util.Map;

import ifba.inf011.memento.classic.EditorMemento;
import ifba.inf011.memento.classic.GraphicEditor;

// Caretaker
public class NamedVersionHistory {

    private final Map<String, EditorMemento> versions;

    public NamedVersionHistory() {
        this.versions = new LinkedHashMap<>();
    }

    public void save(String label, GraphicEditor editor) {
        EditorMemento memento = editor.save(label);
        this.versions.put(label, memento);
        System.out.println("Versão salva: " + label);
    }

    public void restore(String label, GraphicEditor editor) {
        EditorMemento memento = versions.get(label);

        if (memento == null) {
            System.out.println("Versão não encontrada: " + label);
            return;
        }

        editor.restore(memento);
        System.out.println("Versão restaurada: " + label);
    }

    public void listVersions() {
        System.out.println("Versões disponíveis:");
        for (String label : versions.keySet()) {
            System.out.println("  - " + label);
        }
    }
}
