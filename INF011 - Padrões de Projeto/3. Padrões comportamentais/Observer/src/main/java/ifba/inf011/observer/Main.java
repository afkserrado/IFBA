package ifba.inf011.observer;

import ifba.inf011.observer.change_manager.ChangeManager;
import ifba.inf011.observer.change_manager.ManagedEditor;
import ifba.inf011.observer.change_manager.MultiEditorStatusPanel;
import ifba.inf011.observer.change_manager.SaveMetricsObserver;
import ifba.inf011.observer.classic.PreviewObserver;
import ifba.inf011.observer.classic.StatusBarObserver;
import ifba.inf011.observer.classic.TextEditor;
import ifba.inf011.observer.classic.WordCountObserver;
import ifba.inf011.observer.event_manager.BackupOnSaveListener;
import ifba.inf011.observer.event_manager.EmailNotificationListener;
import ifba.inf011.observer.event_manager.EventedEditor;
import ifba.inf011.observer.event_manager.LogOpenListener;
import ifba.inf011.observer.push.AutoBackupBeforeChangeListener;
import ifba.inf011.observer.push.DiffPreviewListener;
import ifba.inf011.observer.push.ObservableTextEditor;

// Client
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Implementação 1: Observer canônico com pull ===");

        TextEditor editor = new TextEditor();

        StatusBarObserver status = new StatusBarObserver("Barra de Status");
        WordCountObserver counter = new WordCountObserver();
        PreviewObserver preview = new PreviewObserver();

        editor.attach(status);
        editor.attach(counter);
        editor.attach(preview);

        editor.openFile("artigo.txt", "Observer desacopla publisher e subscribers");
        editor.replaceContent("Observer permite notificações automáticas");
        editor.detach(preview);
        editor.saveFile();

        System.out.println("\n=== Implementação 2: EventManager por tipo de evento ===");

        EventedEditor eventedEditor = new EventedEditor();

        eventedEditor.events.subscribe(
                "open",
                new LogOpenListener("editor.log")
        );

        eventedEditor.events.subscribe(
                "save",
                new EmailNotificationListener("admin@example.com")
        );

        eventedEditor.events.subscribe(
                "save",
                new BackupOnSaveListener()
        );

        eventedEditor.openFile("contrato.txt", "Texto inicial do contrato");
        eventedEditor.replaceContent("Texto revisado do contrato");
        eventedEditor.saveFile();

        System.out.println("\n=== Implementação 3: Push antes da modificação ===");

        ObservableTextEditor observableEditor =
                new ObservableTextEditor("rascunho.txt", "Linha inicial");

        observableEditor.addBeforeChangeListener(new AutoBackupBeforeChangeListener());
        observableEditor.addBeforeChangeListener(new DiffPreviewListener());

        observableEditor.append(" + complemento");
        observableEditor.write("Conteúdo totalmente substituído");

        System.out.println("\nConteúdo final: " + observableEditor.getContent());

        System.out.println("\n=== Implementação 4: ChangeManager centralizado ===");

        ManagedEditor editorA = new ManagedEditor("A");
        ManagedEditor editorB = new ManagedEditor("B");

        MultiEditorStatusPanel panel = new MultiEditorStatusPanel();
        SaveMetricsObserver metrics = new SaveMetricsObserver();

        ChangeManager manager = ChangeManager.getInstance();

        manager.register(editorA, "open", panel);
        manager.register(editorA, "save", panel);
        manager.register(editorB, "open", panel);
        manager.register(editorB, "save", panel);

        manager.register(editorA, "save", metrics);
        manager.register(editorB, "save", metrics);

        editorA.openFile("a.txt", "Conteúdo A");
        editorB.openFile("b.txt", "Conteúdo B");

        editorA.saveFile();
        editorB.saveFile();

        manager.unregister(editorB, "save", panel);

        editorB.saveFile();
    }
}
