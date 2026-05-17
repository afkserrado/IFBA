package ifba.inf011.s23_2.concrete_factorys;

import ifba.inf011.s23_2.concrete_products.HTTPProtocolo2;
import ifba.inf011.s23_2.interfaces.Fabrica2;
import ifba.inf011.s23_2.interfaces.Protocolo2;

// Concrete factory
public class HTTPFabrica2 implements Fabrica2 {
    @Override
    public Protocolo2 createProtocolo(Integer porta, String host) {
        return new HTTPProtocolo2(porta, host);
    }
}
