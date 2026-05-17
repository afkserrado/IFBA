package ifba.inf011.s23_2.concrete_products;

import ifba.inf011.s23_2.interfaces.Request;

// Concrete product
public class HTTPRequest2 implements Request {
    private String header;
    private String body;

    public HTTPRequest2(String header, String body) {
        this.header = header;
        this.body = body;
    }
}
