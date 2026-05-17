package ifba.inf011.s23_2.concrete_products;

import ifba.inf011.s23_2.interfaces.Protocolo;
import ifba.inf011.s23_2.interfaces.Request;
import ifba.inf011.s23_2.interfaces.Response;

// Concrete product
public class FTPProtocolo implements Protocolo {
    
    private Integer porta;
    private String host;

    public FTPProtocolo(Integer porta, String host) {
        this.porta = porta;
        this.host = host;
    }

    @Override
    public void open() {
        System.out.println("Método open: FTPPProtocolo");
    }

    @Override
    public void message(Request req, Response resp) {
        System.out.println("Método message: FTPPProtocolo");
    }

    @Override
    public void close() {
        System.out.println("Método close: FTPPProtocolo");
    }
}
