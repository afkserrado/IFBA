package ifba.inf011.observer.classic;

// Concrete Observer
public class StatusBarObserver implements EditorObserver {

    private final String name;

    public StatusBarObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(TextEditor editor) {
        System.out.println("[" + name + "] Arquivo atual: "
                + editor.getFileName()
                + " | Operação: "
                + editor.getLastOperation());
    }
}
