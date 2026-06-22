package ifba.inf011.observer.event_manager;

import ifba.inf011.observer.domain.EditorEvent;

// Observer
public interface EventListener {
    void update(EditorEvent event);
}
