package ifba.inf011.template_method.factory_method;

import ifba.inf011.template_method.classic.Network;
import ifba.inf011.template_method.domain.SocialPost;

// Abstract Class
public abstract class SocialPublisherApplication {

    // Template Method
    public final boolean publish(SocialPost post) {
        if (!canPublish(post)) {
            System.out.println("Aplicação: publicação inválida.");
            return false;
        }

        aboutToCreateNetwork(post);

        Network network = doCreateNetwork();

        registerNetwork(network);

        boolean result = network.post(post);

        afterPublish(post, result);

        return result;
    }

    // Concrete Operation
    protected boolean canPublish(SocialPost post) {
        return post != null
                && post.getText() != null
                && !post.getText().isBlank();
    }

    // Hook Operation
    protected void aboutToCreateNetwork(SocialPost post) {
    }

    // Factory Method
    protected abstract Network doCreateNetwork();

    // Concrete Operation
    protected void registerNetwork(Network network) {
        System.out.println("Aplicação: rede selecionada -> " + network.name());
    }

    // Hook Operation
    protected void afterPublish(SocialPost post, boolean result) {
    }
}
