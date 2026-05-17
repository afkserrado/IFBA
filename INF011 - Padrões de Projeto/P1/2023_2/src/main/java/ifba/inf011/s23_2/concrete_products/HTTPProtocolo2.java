package ifba.inf011.s23_2.concrete_products;

import ifba.inf011.s23_2.interfaces.Protocolo2;

// Concrete product
public class HTTPProtocolo2 implements Protocolo2 {
    
    private Integer porta;
    private String host;
    private HTTPRequest2 request;
    private HTTPResponse response;

    public HTTPProtocolo2(Integer porta, String host) {
        this.porta = porta;
        this.host = host;
    }

    @Override
    public void open() {
        System.out.println("Método open: HTTPProtocolo");
    }

    @Override
    public void message(String header, String body) {       
        this.request = new HTTPRequest2(header, body);
        this.response = new HTTPResponse();
        System.out.println("Método message: HTTPProtocolo");
    }

    @Override
    public void close() {
        System.out.println("Método close: HTTPProtocolo");
    }
}
