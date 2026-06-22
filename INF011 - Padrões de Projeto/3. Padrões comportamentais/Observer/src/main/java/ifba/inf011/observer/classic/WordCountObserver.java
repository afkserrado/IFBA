package ifba.inf011.observer.classic;

// Concrete Observer
public class WordCountObserver implements EditorObserver {

    @Override
    public void update(TextEditor editor) {
        String content = editor.getContent();

        int count = 0;
        if (content != null && !content.isBlank()) {
            count = content.trim().split("\\s+").length;
        }

        System.out.println("[Contador] Quantidade de palavras: " + count);
    }
}
