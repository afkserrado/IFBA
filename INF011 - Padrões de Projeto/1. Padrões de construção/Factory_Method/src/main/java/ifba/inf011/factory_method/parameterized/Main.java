package ifba.inf011.factory_method.parameterized;

import ifba.inf011.factory_method.parameterized.creators.Creator;
import ifba.inf011.factory_method.parameterized.enums.ProductId;

// Cliente
public class Main {
    public static void run() {
        Creator creator = new Creator();

        creator.anOperation(ProductId.A);
        creator.anOperation(ProductId.B);
    }
}