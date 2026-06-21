package ifba.inf011.proxy.domain;

// Real subject supporting data
public class Video {
    private final String id;
    private final String title;
    private final String data;

    public Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }
}