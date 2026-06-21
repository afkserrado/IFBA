package ifba.inf011.factory_method.template_based;

import ifba.inf011.factory_method.template_based.creators.Creator;
import ifba.inf011.factory_method.template_based.products.ProductA;
import ifba.inf011.factory_method.template_based.products.ProductB;

public class Main {

    public static void run() {
        // Não há subclasse de Creator — o tipo do produto é passado como parâmetro
        Creator<ProductA> creatorA = new Creator<>(ProductA.class);
        Creator<ProductB> creatorB = new Creator<>(ProductB.class);

        creatorA.anOperation();
        creatorB.anOperation();

        /*
        Saída:
        TemplateBased: doSomething from ProductA
        TemplateBased: doSomething from ProductB
        */
    }
}