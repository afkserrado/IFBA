package ifba.inf011.observer.push;

// Subject
public interface BeforeChangeSubject {
    void addBeforeChangeListener(BeforeChangeListener listener);
    void removeBeforeChangeListener(BeforeChangeListener listener);
    void notifyBeforeChange(String oldContent, String newContent);
}
