package ifba.inf011.template_method.hooks;

import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class FacebookPublishingWorkflow extends PublishingWorkflow {

    @Override
    protected void beforeLogin(SocialPost post) {
        System.out.println("Facebook: preparando autenticação com permissões de página.");
    }

    @Override
    protected boolean logIn() {
        System.out.println("Facebook: login realizado.");
        return true;
    }

    @Override
    protected boolean send(SocialPost post) {
        System.out.println("Facebook: post publicado -> " + post.getText());
        return true;
    }

    @Override
    protected void afterSend(SocialPost post, boolean result) {
        System.out.println("Facebook: registrando alcance inicial da publicação.");
    }

    @Override
    protected void logOut() {
        System.out.println("Facebook: logout realizado.");
    }

    @Override
    protected String networkName() {
        return "Facebook";
    }
}
