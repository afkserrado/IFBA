package ifba.inf011.p2.s26_1.q3.proxy;

public class Autenticador {

    public boolean autenticar(Credencial credencial) {
        if (credencial == null) {
            return false;
        }

        return "admin".equals(credencial.getUsuario())
                && "123".equals(credencial.getToken());
    }
}