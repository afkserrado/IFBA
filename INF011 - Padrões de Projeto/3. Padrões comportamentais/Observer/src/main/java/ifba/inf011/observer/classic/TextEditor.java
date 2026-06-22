package ifba.inf011.observer.classic;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject
public class TextEditor implements EditorSubject {

    private final List<EditorObserver> observers;
    private String fileName;
    private String content;
    private String lastOperation;

    public TextEditor() {
        this.observers = new ArrayList<>();
        this.fileName = "";
        this.content = "";
        this.lastOperation = "";
    }

    @Override
    public void attach(EditorObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(EditorObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (EditorObserver observer : observers) {
            observer.update(this);
        }
    }

    public void openFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.lastOperation = "open";
        notifyObservers();
    }

    public void saveFile() {
        this.lastOperation = "save";
        notifyObservers();
    }

    public void replaceContent(String content) {
        this.content = content;
        this.lastOperation = "replace";
        notifyObservers();
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public String getLastOperation() {
        return lastOperation;
    }
}
