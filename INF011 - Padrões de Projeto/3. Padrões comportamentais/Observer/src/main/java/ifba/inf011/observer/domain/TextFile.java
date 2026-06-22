package ifba.inf011.observer.domain;

// Event Data
public class TextFile {

    private final String name;
    private final String content;

    public TextFile(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
