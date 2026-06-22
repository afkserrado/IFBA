package ifba.inf011.observer.push;

import ifba.inf011.observer.domain.EditorEvent;

// Concrete Observer
public class DiffPreviewListener implements BeforeChangeListener {

    @Override
    public void beforeChange(EditorEvent event) {
        System.out.println("[Diff] Arquivo: " + event.getFileName());
        System.out.println("  Antes : " + event.getOldContent());
        System.out.println("  Depois: " + event.getNewContent());
    }
}
