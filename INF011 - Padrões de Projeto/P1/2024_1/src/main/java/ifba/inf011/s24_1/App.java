package ifba.inf011.s24_1;

import ifba.inf011.s24_1.builders_concretos.BuilderCruzado;
import ifba.inf011.s24_1.builders_concretos.BuilderEspada;
import ifba.inf011.s24_1.directors.Director;
import ifba.inf011.s24_1.products.Cruzado;

public class App {
    public static void main(String[] args) {
        BuilderEspada bA = new BuilderEspada();
        BuilderCruzado bP = new BuilderCruzado();

        Director d = new Director(bP, bA);
        Cruzado cruz = (Cruzado) d.criarPersonagemInicial();

        System.out.println(cruz);
    }
}
