package ifba.inf011.parameterized;

import ifba.inf011.parameterized.creators.Creator;
import ifba.inf011.parameterized.enums.ProductId;

public class Main {
    public static void main(String[] args) {
        Creator creator = new Creator();
        creator.anOperation(ProductId.A);
        creator.anOperation(ProductId.B);
    }
}