package ifba.inf011.s23_2;

import ifba.inf011.s23_2.concrete_factorys.HTTPFabrica;
import ifba.inf011.s23_2.concrete_products.FTPPResponse;
import ifba.inf011.s23_2.concrete_products.HTTPRequest;
import ifba.inf011.s23_2.interfaces.Protocolo;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.runQ1();
    }

    private void runQ1() {
        System.out.println("Questão 1");
        Protocolo protocolo = new HTTPFabrica().createProtocolo(Integer.valueOf(21), "http.inf011.ifba.edu.br");
        protocolo.open();
        protocolo.message(new HTTPRequest(), new FTPPResponse()); 
        protocolo.close();
        System.out.println();
    }
}
