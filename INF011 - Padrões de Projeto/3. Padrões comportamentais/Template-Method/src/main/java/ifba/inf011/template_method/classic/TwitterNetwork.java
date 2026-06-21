package ifba.inf011.template_method.classic;

// Concrete Class
public class TwitterNetwork extends Network {

    public TwitterNetwork(String userName, String password) {
        super(userName, password);
    }

    @Override
    public String name() {
        return "Twitter";
    }

    @Override
    protected boolean logIn(String userName, String password) {
        System.out.println("Twitter: autenticando usuário " + userName + ".");
        return password != null && password.length() >= 3;
    }

    @Override
    protected boolean sendData(byte[] data) {
        System.out.println("Twitter: publicando tweet '" + new String(data) + "'.");
        return true;
    }

    @Override
    protected void logOut() {
        System.out.println("Twitter: encerrando sessão de " + getUserName() + ".");
    }
}
