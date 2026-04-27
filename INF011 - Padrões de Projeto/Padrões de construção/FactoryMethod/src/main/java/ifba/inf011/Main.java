package ifba.inf011;

import ifba.inf011.Creators.CreatorProductA;
import ifba.inf011.Creators.CreatorProductB;
import ifba.inf011.Interfaces.Creator;

// Cliente
public class Main {
    public static void main(String[] args) {
        Creator creatorProductA = new CreatorProductA();
        Creator creatorProductB = new CreatorProductB();

        creatorProductA.someOperation();
        creatorProductB.someOperation();
    }
}
