package ifba.inf011.classic;

import ifba.inf011.classic.creators.CreatorProductA;
import ifba.inf011.classic.creators.CreatorProductB;
import ifba.inf011.classic.interfaces.Creator;

// Cliente
public class Main {
    public static void main(String[] args) {
        
        Creator creatorProductA = new CreatorProductA();
        Creator creatorProductB = new CreatorProductB();

        creatorProductA.someOperation();
        creatorProductB.someOperation(); 
    }
}
