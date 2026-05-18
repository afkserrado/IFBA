package ifba.inf011.s23_1;

import ifba.inf011.s23_1.builder.PreparoDirector;
import ifba.inf011.s23_1.builder.PreparoObjectBuilder;
import ifba.inf011.s23_1.builder.ValorNutricionalBuilder;
import ifba.inf011.s23_1.classes.Preparo;
import ifba.inf011.s23_1.classes.ValorNutricional;

public class App {
    
    public void q2() {
        PreparoObjectBuilder builder1 = new PreparoObjectBuilder();
        PreparoDirector director = new PreparoDirector(builder1);

        director.fazerOvoFrito();
        Preparo preparo = builder1.build();

        ValorNutricionalBuilder builder2 = new ValorNutricionalBuilder();
        director.setBuilder(builder2);
        ValorNutricional vN = builder2.build();

        System.out.println(preparo);
        System.out.println(vN);
    }
    
    public static void main(String[] args) {
        App app = new App();

        app.q2();
    }
}
