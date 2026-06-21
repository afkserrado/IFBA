package ifba.inf011.template_method.factory_method;

import ifba.inf011.template_method.classic.FacebookNetwork;
import ifba.inf011.template_method.classic.Network;
import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class FacebookPublisherApplication extends SocialPublisherApplication {

    private final String userName;
    private final String password;

    public FacebookPublisherApplication(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected void aboutToCreateNetwork(SocialPost post) {
        System.out.println("Aplicação Facebook: preparando publicação.");
    }

    @Override
    protected Network doCreateNetwork() {
        return new FacebookNetwork(userName, password);
    }

    @Override
    protected void afterPublish(SocialPost post, boolean result) {
        System.out.println("Aplicação Facebook: resultado da publicação = " + result + ".");
    }
}
