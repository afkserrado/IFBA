package ifba.inf011.observer.change_manager;

// Observer
public interface ManagedEditorObserver {
    void update(ManagedEditorSubject subject, String eventType);
}
