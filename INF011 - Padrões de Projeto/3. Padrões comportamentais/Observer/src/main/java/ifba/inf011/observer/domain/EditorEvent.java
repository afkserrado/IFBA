package ifba.inf011.observer.domain;

// Event Data
public class EditorEvent {

    private final String eventType;
    private final String fileName;
    private final String oldContent;
    private final String newContent;

    public EditorEvent(
            String eventType,
            String fileName,
            String oldContent,
            String newContent) {

        this.eventType = eventType;
        this.fileName = fileName;
        this.oldContent = oldContent;
        this.newContent = newContent;
    }

    public String getEventType() {
        return eventType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getOldContent() {
        return oldContent;
    }

    public String getNewContent() {
        return newContent;
    }
}
