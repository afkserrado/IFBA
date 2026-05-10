package ifba.inf011.parameterized;

import ifba.inf011.parameterized.creators.Creator;
import ifba.inf011.parameterized.enums.ProductId;

// Cliente
public class Main {
    public static void run() {
        Creator creator = new Creator();

        creator.anOperation(ProductId.A);
        creator.anOperation(ProductId.B);
    }
}