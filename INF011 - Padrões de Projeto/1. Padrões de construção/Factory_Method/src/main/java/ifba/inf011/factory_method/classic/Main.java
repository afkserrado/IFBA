package ifba.inf011.factory_method.classic;

import ifba.inf011.factory_method.classic.creators.CreatorProductA;
import ifba.inf011.factory_method.classic.creators.CreatorProductB;
import ifba.inf011.factory_method.classic.interfaces.Creator;

// Cliente
public class Main {
    public static void run() {
        
        Creator creatorProductA = new CreatorProductA();
        Creator creatorProductB = new CreatorProductB();

        creatorProductA.someOperation();
        creatorProductB.someOperation(); 
    }
}
