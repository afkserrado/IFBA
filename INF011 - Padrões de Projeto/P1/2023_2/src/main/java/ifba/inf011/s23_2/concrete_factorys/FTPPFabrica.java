package ifba.inf011.s23_2.concrete_factorys;

import ifba.inf011.s23_2.concrete_products.FTPPRequest;
import ifba.inf011.s23_2.concrete_products.FTPPResponse;
import ifba.inf011.s23_2.concrete_products.FTPProtocolo;
import ifba.inf011.s23_2.interfaces.Fabrica;
import ifba.inf011.s23_2.interfaces.Protocolo;
import ifba.inf011.s23_2.interfaces.Request;
import ifba.inf011.s23_2.interfaces.Response;

public class FTPPFabrica implements Fabrica {
    
    @Override
    public Request createRequest() {
        return new FTPPRequest();
    }

    @Override
    public Response createResponse() {
        return new FTPPResponse();
    }

    @Override
    public Protocolo createProtocolo(Integer porta, String host) {
        return new FTPProtocolo(porta, host);
    }
}
