package ifba.inf011.observer.classic;

// Concrete Observer
public class PreviewObserver implements EditorObserver {

    @Override
    public void update(TextEditor editor) {
        System.out.println("[Preview] Conteúdo atual: " + editor.getContent());
    }
}
