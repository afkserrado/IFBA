package ifba.inf011.observer.change_manager;

// Concrete Observer
public class SaveMetricsObserver implements ManagedEditorObserver {

    private int saveCount;

    public SaveMetricsObserver() {
        this.saveCount = 0;
    }

    @Override
    public void update(ManagedEditorSubject subject, String eventType) {
        if ("save".equals(eventType)) {
            saveCount++;
            System.out.println("[Métricas] Total de salvamentos observados: "
                    + saveCount);
        }
    }
}
