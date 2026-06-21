package ifba.inf011.abstract_factory.classic;

import ifba.inf011.abstract_factory.concrete_factories.ConcreteFactory1;
import ifba.inf011.abstract_factory.concrete_factories.ConcreteFactory2;
import ifba.inf011.abstract_factory.interfaces.AbstractFactory;
import ifba.inf011.abstract_factory.interfaces.AbstractProductA;
import ifba.inf011.abstract_factory.interfaces.AbstractProductB;

// Usa apenas as interfaces de AbstractFactory e AbstractProduct
public class Main {

    private AbstractProductA productA;
    private AbstractProductB productB;

    public Main(AbstractFactory factory) {
        this.productA = factory.createProductA();
        this.productB = factory.createProductB();
    }

    public void run() {
        System.out.println(productB.operationB());
        System.out.println(productB.collaborateWith(productA));
    }

    public static void execute() {
        // Exemplo: Modern
        System.out.println("--- Família 1 ---");
        Main client1 = new Main(new ConcreteFactory1());
        client1.run();

        System.out.println();

        // Exemplo: Victorian
        System.out.println("--- Família 2 ---");
        Main client2 = new Main(new ConcreteFactory2());
        client2.run();

        /*
        Saída:
        --- Família 1 ---
        Resultado de ProductB1
        ProductB1 colaborando com [ Resultado de ProductA1 ]
        --- Família 2 ---
        Resultado de ProductB2
        ProductB2 colaborando com [ Resultado de ProductA2 ]
        */
    }
}