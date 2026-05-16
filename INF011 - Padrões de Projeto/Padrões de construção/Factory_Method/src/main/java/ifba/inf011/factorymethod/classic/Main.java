package ifba.inf011.factorymethod.classic;

import ifba.inf011.factorymethod.classic.creators.CreatorProductA;
import ifba.inf011.factorymethod.classic.creators.CreatorProductB;
import ifba.inf011.factorymethod.classic.interfaces.Creator;

// Cliente
public class Main {
    public static void run() {
        
        Creator creatorProductA = new CreatorProductA();
        Creator creatorProductB = new CreatorProductB();

        creatorProductA.someOperation();
        creatorProductB.someOperation(); 
    }
}
