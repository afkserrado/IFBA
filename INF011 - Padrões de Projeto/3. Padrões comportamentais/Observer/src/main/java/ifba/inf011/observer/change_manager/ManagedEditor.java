package ifba.inf011.observer.change_manager;

// Concrete Subject
public class ManagedEditor implements ManagedEditorSubject {

    private final String id;
    private String fileName;
    private String content;

    public ManagedEditor(String id) {
        this.id = id;
        this.fileName = "";
        this.content = "";
    }

    public void openFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;

        ChangeManager.getInstance().notify(this, "open");
    }

    public void saveFile() {
        ChangeManager.getInstance().notify(this, "save");
    }

    public void replaceContent(String content) {
        this.content = content;

        ChangeManager.getInstance().notify(this, "change");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getContent() {
        return content;
    }
}
