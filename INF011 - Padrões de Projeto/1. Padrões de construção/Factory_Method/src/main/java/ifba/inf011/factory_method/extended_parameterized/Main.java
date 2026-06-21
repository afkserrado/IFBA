package ifba.inf011.factory_method.extended_parameterized;

import ifba.inf011.factory_method.extended_parameterized.creators.Creator;
import ifba.inf011.factory_method.extended_parameterized.creators.ExtendedCreator;
import ifba.inf011.factory_method.extended_parameterized.enums.BasicProductType;
import ifba.inf011.factory_method.extended_parameterized.enums.ExtendedProductType;

public class Main {

    public static void run() {
        Creator creator = new Creator();
        creator.anOperation(BasicProductType.A);
        creator.anOperation(BasicProductType.B);

        System.out.println("--- ExtendedCreator ---");
        ExtendedCreator extended = new ExtendedCreator();
        extended.anOperation(BasicProductType.A);   // delega ao super
        extended.anOperation(BasicProductType.B);   // delega ao super
        extended.anOperation(ExtendedProductType.C); // novo produto

        /*
        Saída:
        ExtendedParameterized: doSomething from ProductA
        ExtendedParameterized: doSomething from ProductB
        --- ExtendedCreator ---
        ExtendedParameterized: doSomething from ProductA
        ExtendedParameterized: doSomething from ProductB
        ExtendedParameterized: doSomething from ProductC (extended)
        */
    }
}