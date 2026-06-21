package ifba.inf011.template_method.hooks;

import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class TwitterPublishingWorkflow extends PublishingWorkflow {

    @Override
    protected boolean logIn() {
        System.out.println("Twitter: login realizado.");
        return true;
    }

    @Override
    protected void beforeSend(SocialPost post) {
        System.out.println("Twitter: verificando limite de caracteres.");
    }

    @Override
    protected boolean send(SocialPost post) {
        System.out.println("Twitter: tweet publicado -> " + post.getText());
        return true;
    }

    @Override
    protected void logOut() {
        System.out.println("Twitter: logout realizado.");
    }

    @Override
    protected String networkName() {
        return "Twitter";
    }
}
