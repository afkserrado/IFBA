package ifba.inf011.observer.change_manager;

// Concrete Observer
public class MultiEditorStatusPanel implements ManagedEditorObserver {

    @Override
    public void update(ManagedEditorSubject subject, String eventType) {
        System.out.println("[Painel multi-editor] Editor "
                + subject.getId()
                + " disparou evento "
                + eventType
                + " no arquivo "
                + subject.getFileName());
    }
}
