package ifba.inf011.observer.push;

import ifba.inf011.observer.domain.EditorEvent;

// Observer
public interface BeforeChangeListener {
    void beforeChange(EditorEvent event);
}
