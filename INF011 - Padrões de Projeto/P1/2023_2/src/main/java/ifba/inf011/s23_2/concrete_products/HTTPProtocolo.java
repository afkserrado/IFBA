package ifba.inf011.s23_2.concrete_products;

import ifba.inf011.s23_2.interfaces.Protocolo;
import ifba.inf011.s23_2.interfaces.Request;
import ifba.inf011.s23_2.interfaces.Response;

// Concrete product
public class HTTPProtocolo implements Protocolo {
    
    private Integer porta;
    private String host;

    public HTTPProtocolo(Integer porta, String host) {
        this.porta = porta;
        this.host = host;
    }

    @Override
    public void open() {
        System.out.println("Método open: HTTPProtocolo");
    }

    @Override
    public void message(Request req, Response resp) {       
        System.out.println("Método message: HTTPProtocolo");
    }

    @Override
    public void close() {
        System.out.println("Método close: HTTPProtocolo");
    }
}
