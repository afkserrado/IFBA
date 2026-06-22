package ifba.inf011.observer.event_manager;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Observer
public class BackupOnSaveListener implements EventListener {

    @Override
    public void update(EditorEvent event) {
        System.out.println("[Backup] Backup criado para o arquivo "
                + event.getFileName());
    }
}
