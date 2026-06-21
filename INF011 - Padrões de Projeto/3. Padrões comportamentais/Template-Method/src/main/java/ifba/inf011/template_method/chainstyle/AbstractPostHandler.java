package ifba.inf011.template_method.chainstyle;

import ifba.inf011.template_method.domain.SocialPost;

// Abstract Class / Base Handler
public abstract class AbstractPostHandler implements PostHandler {

    private PostHandler next;

    protected AbstractPostHandler() {
    }

    protected AbstractPostHandler(PostHandler next) {
        this.next = next;
    }

    @Override
    public void setNext(PostHandler next) {
        this.next = next;
    }

    // Template Method
    @Override
    public final boolean publish(SocialPost post) {
        if (!canPublish(post)) {
            if (next != null) {
                return next.publish(post);
            }

            System.out.println("Nenhuma rede disponível para publicar em: " + post.getTargetNetwork());
            return false;
        }

        doPreProcess(post);

        if (!doLogIn()) {
            return false;
        }

        boolean result = doSend(post);

        doLogOut();

        saveAudit(post, result);

        return result;
    }

    // Primitive Operation
    protected abstract boolean canPublish(SocialPost post);

    // Hook Operation
    protected void doPreProcess(SocialPost post) {
    }

    // Primitive Operation
    protected abstract boolean doLogIn();

    // Primitive Operation
    protected abstract boolean doSend(SocialPost post);

    // Primitive Operation
    protected abstract void doLogOut();

    // Concrete Operation
    protected void saveAudit(SocialPost post, boolean result) {
        System.out.println("Auditoria: publicação em "
                + post.getTargetNetwork()
                + " finalizada com resultado "
                + result
                + ".");
    }
}
