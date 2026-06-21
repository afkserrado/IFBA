package ifba.inf011.strategy.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Domain Data
public class Route {

    private final String description;
    private final List<String> checkpoints;

    public Route(String description, List<String> checkpoints) {
        this.description = description;
        this.checkpoints = new ArrayList<>(checkpoints);
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCheckpoints() {
        return Collections.unmodifiableList(checkpoints);
    }

    @Override
    public String toString() {
        return description + " -> " + checkpoints;
    }
}