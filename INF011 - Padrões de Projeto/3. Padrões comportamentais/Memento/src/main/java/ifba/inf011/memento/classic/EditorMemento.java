package ifba.inf011.memento.classic;

import java.time.LocalDateTime;

// Memento - Narrow Interface
public interface EditorMemento {
    String getName();
    LocalDateTime getDate();
}
