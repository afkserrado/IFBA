package ifba.inf011.s23_2;

import ifba.inf011.s23_2.concrete_factorys.HTTPFabrica;
import ifba.inf011.s23_2.concrete_factorys.HTTPFabrica2;
import ifba.inf011.s23_2.concrete_products.FTPPResponse;
import ifba.inf011.s23_2.concrete_products.HTTPRequest;
import ifba.inf011.s23_2.interfaces.Protocolo;
import ifba.inf011.s23_2.interfaces.Protocolo2;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.runQ1();
        app.runQ2();
    }

    private void runQ1() {
        System.out.println("Questão 1");
        Protocolo protocolo = new HTTPFabrica().createProtocolo(Integer.valueOf(21), "http.inf011.ifba.edu.br");
        protocolo.open();
        protocolo.message(new HTTPRequest(), new FTPPResponse()); 
        protocolo.close();
        System.out.println();
    }

    private void runQ2() {

        System.out.println("Questão 2");

        Protocolo2 protocolo =
            new HTTPFabrica2()
                .createProtocolo(
                    Integer.valueOf(21),
                    "http.inf011.ifba.edu.br"
                );

        protocolo.open();
        protocolo.message("header", "body");
        protocolo.close();
        System.out.println();
    }
}
