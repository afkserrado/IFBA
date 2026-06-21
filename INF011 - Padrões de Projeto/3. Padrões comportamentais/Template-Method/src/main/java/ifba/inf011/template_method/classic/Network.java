package ifba.inf011.template_method.classic;

import java.nio.charset.StandardCharsets;

import ifba.inf011.template_method.domain.SocialPost;

// Abstract Class
public abstract class Network {

    private final String userName;
    private final String password;

    protected Network(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Template Method
    public final boolean post(SocialPost post) {
        if (logIn(userName, password)) {
            boolean result = sendData(post.getText().getBytes(StandardCharsets.UTF_8));
            logOut();
            return result;
        }

        return false;
    }

    public abstract String name();

    // Primitive Operation
    protected abstract boolean logIn(String userName, String password);

    // Primitive Operation
    protected abstract boolean sendData(byte[] data);

    // Primitive Operation
    protected abstract void logOut();

    protected String getUserName() {
        return userName;
    }
}
