package ifba.inf011.observer.event_manager;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Observer
public class EmailNotificationListener implements EventListener {

    private final String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(EditorEvent event) {
        System.out.println("[E-mail para " + email + "] Evento '"
                + event.getEventType()
                + "' no arquivo "
                + event.getFileName());
    }
}
