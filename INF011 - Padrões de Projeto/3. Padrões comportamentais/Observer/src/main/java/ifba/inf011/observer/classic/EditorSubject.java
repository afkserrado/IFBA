package ifba.inf011.observer.classic;

// Subject
public interface EditorSubject {
    void attach(EditorObserver observer);
    void detach(EditorObserver observer);
    void notifyObservers();
}
