package ifba.inf011.observer.change_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Change Manager / Mediator / Singleton
public class ChangeManager {

    private static final ChangeManager INSTANCE = new ChangeManager();

    private final Map<ManagedEditorSubject, Map<String, List<ManagedEditorObserver>>> subscriptions;

    private ChangeManager() {
        this.subscriptions = new HashMap<>();
    }

    public static ChangeManager getInstance() {
        return INSTANCE;
    }

    public void register(
            ManagedEditorSubject subject,
            String eventType,
            ManagedEditorObserver observer) {

        subscriptions
                .computeIfAbsent(subject, key -> new HashMap<>())
                .computeIfAbsent(eventType, key -> new ArrayList<>())
                .add(observer);
    }

    public void unregister(
            ManagedEditorSubject subject,
            String eventType,
            ManagedEditorObserver observer) {

        Map<String, List<ManagedEditorObserver>> subjectEvents = subscriptions.get(subject);

        if (subjectEvents == null) {
            return;
        }

        List<ManagedEditorObserver> observers = subjectEvents.get(eventType);

        if (observers == null) {
            return;
        }

        observers.remove(observer);
    }

    public void notify(
            ManagedEditorSubject subject,
            String eventType) {

        Map<String, List<ManagedEditorObserver>> subjectEvents = subscriptions.get(subject);

        if (subjectEvents == null) {
            return;
        }

        List<ManagedEditorObserver> observers = subjectEvents.get(eventType);

        if (observers == null) {
            return;
        }

        for (ManagedEditorObserver observer : new ArrayList<>(observers)) {
            observer.update(subject, eventType);
        }
    }
}
