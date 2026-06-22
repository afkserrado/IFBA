package ifba.inf011.memento.strict;

import java.time.LocalDateTime;

// Memento - Narrow Interface
public interface RestorableMemento {
    String getName();
    LocalDateTime getDate();
    void restore();
}
