package ifba.inf011.observer.event_manager;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Observer
public class LogOpenListener implements EventListener {

    private final String logFileName;

    public LogOpenListener(String logFileName) {
        this.logFileName = logFileName;
    }

    @Override
    public void update(EditorEvent event) {
        System.out.println("[Log: " + logFileName + "] Arquivo aberto: "
                + event.getFileName());
    }
}
