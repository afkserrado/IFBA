package ifba.inf011.observer.push;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Subject
public class ObservableTextEditor implements BeforeChangeSubject {

    private final List<BeforeChangeListener> listeners;
    private final String fileName;
    private String content;

    public ObservableTextEditor(String fileName, String content) {
        this.listeners = new ArrayList<>();
        this.fileName = fileName;
        this.content = content;
    }

    @Override
    public void addBeforeChangeListener(BeforeChangeListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeBeforeChangeListener(BeforeChangeListener listener) {
        this.listeners.remove(listener);
    }

    @Override
    public void notifyBeforeChange(String oldContent, String newContent) {
        EditorEvent event = new EditorEvent(
                "beforeChange",
                fileName,
                oldContent,
                newContent
        );

        for (BeforeChangeListener listener : new ArrayList<>(listeners)) {
            listener.beforeChange(event);
        }
    }

    public void append(String text) {
        String futureContent = this.content + text;

        notifyBeforeChange(this.content, futureContent);

        this.content = futureContent;
    }

    public void write(String content) {
        notifyBeforeChange(this.content, content);

        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
