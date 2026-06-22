package ifba.inf011.observer.push;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Observer
public class AutoBackupBeforeChangeListener implements BeforeChangeListener {

    @Override
    public void beforeChange(EditorEvent event) {
        System.out.println("[AutoBackup] Backup do conteúdo antigo de "
                + event.getFileName()
                + ": "
                + event.getOldContent());
    }
}
