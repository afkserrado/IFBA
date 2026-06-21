package ifba.inf011.template_method.chainstyle;

import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class FacebookPostHandler extends AbstractPostHandler {

    public FacebookPostHandler() {
    }

    public FacebookPostHandler(PostHandler next) {
        super(next);
    }

    @Override
    protected boolean canPublish(SocialPost post) {
        return "facebook".equalsIgnoreCase(post.getTargetNetwork());
    }

    @Override
    protected void doPreProcess(SocialPost post) {
        System.out.println("Facebook Handler: pré-processando imagem e texto.");
    }

    @Override
    protected boolean doLogIn() {
        System.out.println("Facebook Handler: login realizado.");
        return true;
    }

    @Override
    protected boolean doSend(SocialPost post) {
        System.out.println("Facebook Handler: post publicado -> " + post.getText());
        return true;
    }

    @Override
    protected void doLogOut() {
        System.out.println("Facebook Handler: logout realizado.");
    }
}
