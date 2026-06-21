package ifba.inf011.template_method.factory_method;

import ifba.inf011.template_method.classic.Network;
import ifba.inf011.template_method.classic.TwitterNetwork;
import ifba.inf011.template_method.domain.SocialPost;

// Concrete Class
public class TwitterPublisherApplication extends SocialPublisherApplication {

    private final String userName;
    private final String password;

    public TwitterPublisherApplication(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected Network doCreateNetwork() {
        return new TwitterNetwork(userName, password);
    }

    @Override
    protected void afterPublish(SocialPost post, boolean result) {
        System.out.println("Aplicação Twitter: auditoria da publicação concluída.");
    }
}
