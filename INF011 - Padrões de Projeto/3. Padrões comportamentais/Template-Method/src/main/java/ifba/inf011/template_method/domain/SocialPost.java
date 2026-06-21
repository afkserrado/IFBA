package ifba.inf011.template_method.domain;

// Request Data
public class SocialPost {

    private final String targetNetwork;
    private final String text;

    public SocialPost(String targetNetwork, String text) {
        this.targetNetwork = targetNetwork;
        this.text = text;
    }

    public String getTargetNetwork() {
        return targetNetwork;
    }

    public String getText() {
        return text;
    }
}
