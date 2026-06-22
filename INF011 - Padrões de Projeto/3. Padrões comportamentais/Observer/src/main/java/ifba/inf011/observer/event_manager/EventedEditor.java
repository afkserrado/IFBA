package ifba.inf011.observer.event_manager;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Subject
public class EventedEditor {

    public final EventManager events;
    private String fileName;
    private String content;

    public EventedEditor() {
        this.events = new EventManager("open", "save");
        this.fileName = "";
        this.content = "";
    }

    public void openFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;

        events.notify(
                "open",
                new EditorEvent("open", fileName, null, content)
        );
    }

    public void saveFile() {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalStateException("Abra um arquivo antes de salvar.");
        }

        events.notify(
                "save",
                new EditorEvent("save", fileName, content, content)
        );
    }

    public void replaceContent(String content) {
        this.content = content;
    }
}
