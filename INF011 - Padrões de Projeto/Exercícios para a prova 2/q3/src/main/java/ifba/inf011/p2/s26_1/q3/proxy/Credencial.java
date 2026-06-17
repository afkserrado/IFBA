package ifba.inf011.p2.s26_1.q3.proxy;

public class Credencial {

    private String usuario;
    private String token;

    public Credencial(String usuario, String token) {
        this.usuario = usuario;
        this.token = token;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getToken() {
        return this.token;
    }
}