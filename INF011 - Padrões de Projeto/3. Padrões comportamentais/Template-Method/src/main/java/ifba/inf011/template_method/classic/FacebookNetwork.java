package ifba.inf011.template_method.classic;

// Concrete Class
public class FacebookNetwork extends Network {

    public FacebookNetwork(String userName, String password) {
        super(userName, password);
    }

    @Override
    public String name() {
        return "Facebook";
    }

    @Override
    protected boolean logIn(String userName, String password) {
        System.out.println("Facebook: autenticando usuário " + userName + ".");
        return password != null && !password.isBlank();
    }

    @Override
    protected boolean sendData(byte[] data) {
        System.out.println("Facebook: publicando mensagem '" + new String(data) + "'.");
        return true;
    }

    @Override
    protected void logOut() {
        System.out.println("Facebook: encerrando sessão de " + getUserName() + ".");
    }
}
