package ifba.inf011.template_method.hooks;

import ifba.inf011.template_method.domain.SocialPost;

// Abstract Class
public abstract class PublishingWorkflow {

    // Template Method
    public final boolean publish(SocialPost post) {
        SocialPost preparedPost = preparePost(post);

        beforeLogin(preparedPost);

        if (!logIn()) {
            System.out.println(networkName() + ": falha no login.");
            return false;
        }

        beforeSend(preparedPost);

        boolean result = send(preparedPost);

        afterSend(preparedPost, result);

        logOut();

        return result;
    }

    // Concrete Operation
    protected SocialPost preparePost(SocialPost post) {
        String normalizedText = post.getText() == null ? "" : post.getText().trim();
        return new SocialPost(post.getTargetNetwork(), normalizedText);
    }

    // Hook Operation
    protected void beforeLogin(SocialPost post) {
    }

    // Primitive Operation
    protected abstract boolean logIn();

    // Hook Operation
    protected void beforeSend(SocialPost post) {
    }

    // Primitive Operation
    protected abstract boolean send(SocialPost post);

    // Hook Operation
    protected void afterSend(SocialPost post, boolean result) {
    }

    // Primitive Operation
    protected abstract void logOut();

    // Primitive Operation
    protected abstract String networkName();
}
