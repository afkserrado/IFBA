package ifba.inf011.s23_2.concrete_factorys;

import ifba.inf011.s23_2.concrete_products.HTTPProtocolo;
import ifba.inf011.s23_2.concrete_products.HTTPRequest;
import ifba.inf011.s23_2.concrete_products.HTTPResponse;
import ifba.inf011.s23_2.interfaces.Fabrica;
import ifba.inf011.s23_2.interfaces.Protocolo;
import ifba.inf011.s23_2.interfaces.Request;
import ifba.inf011.s23_2.interfaces.Response;

public class HTTPFabrica implements Fabrica {
    
    @Override
    public Request createRequest() {
        return new HTTPRequest();
    }

    @Override
    public Response createResponse() {
        return new HTTPResponse();
    }

    @Override
    public Protocolo createProtocolo(Integer porta, String host) {
        return new HTTPProtocolo(porta, host);
    }
}
