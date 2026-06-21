package ifba.inf011.template_method.chainstyle;

import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class TwitterPostHandler extends AbstractPostHandler {

    public TwitterPostHandler() {
    }

    public TwitterPostHandler(PostHandler next) {
        super(next);
    }

    @Override
    protected boolean canPublish(SocialPost post) {
        return "twitter".equalsIgnoreCase(post.getTargetNetwork());
    }

    @Override
    protected void doPreProcess(SocialPost post) {
        System.out.println("Twitter Handler: encurtando links e validando tamanho.");
    }

    @Override
    protected boolean doLogIn() {
        System.out.println("Twitter Handler: login realizado.");
        return true;
    }

    @Override
    protected boolean doSend(SocialPost post) {
        System.out.println("Twitter Handler: tweet publicado -> " + post.getText());
        return true;
    }

    @Override
    protected void doLogOut() {
        System.out.println("Twitter Handler: logout realizado.");
    }
}
