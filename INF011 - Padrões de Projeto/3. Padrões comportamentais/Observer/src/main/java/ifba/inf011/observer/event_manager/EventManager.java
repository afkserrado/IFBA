package ifba.inf011.observer.event_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ifba.inf011.observer.domain.EditorEvent;

// Event Manager / Subject Helper
public class EventManager {

    private final Map<String, List<EventListener>> listeners;

    public EventManager(String... eventTypes) {
        this.listeners = new HashMap<>();

        for (String eventType : eventTypes) {
            this.listeners.put(eventType, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);

        if (eventListeners == null) {
            throw new IllegalArgumentException("Tipo de evento inexistente: " + eventType);
        }

        eventListeners.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);

        if (eventListeners == null) {
            return;
        }

        eventListeners.remove(listener);
    }

    public void notify(String eventType, EditorEvent event) {
        List<EventListener> eventListeners = listeners.get(eventType);

        if (eventListeners == null) {
            return;
        }

        for (EventListener listener : new ArrayList<>(eventListeners)) {
            listener.update(event);
        }
    }
}
