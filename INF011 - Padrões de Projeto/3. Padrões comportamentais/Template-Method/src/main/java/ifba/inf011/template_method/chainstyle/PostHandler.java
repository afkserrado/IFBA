package ifba.inf011.template_method.chainstyle;

import ifba.inf011.template_method.domain.SocialPost;

// Handler
public interface PostHandler {
    void setNext(PostHandler next);
    boolean publish(SocialPost post);
}
