package ifba.inf011.prototype.classic;

import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype1;
import ifba.inf011.prototype.concrete_prototypes.ConcretePrototype2;
import ifba.inf011.prototype.interfaces.Prototype;

// Cria novos objetos pedindo ao prototype que clone a si mesmo
public class Client {
    
    private Prototype prototype;

    public Client (Prototype prototype) {
        this.prototype = prototype;
    } 

    public Prototype operation() {
        return prototype.clone();
    }

    public static void main(String[] args) {
        Client client1 = new Client(new ConcretePrototype1("dados originais"));
        ConcretePrototype1 clone1 = (ConcretePrototype1) client1.operation();
        System.out.println(clone1.getField());

        Client client2 = new Client(new ConcretePrototype2(42));
        ConcretePrototype2 clone2 = (ConcretePrototype2) client2.operation();
        System.out.println(clone2.getValue());

        Client client3 = new Client(new ConcretePrototype2(clone2));
        ConcretePrototype2 clone3 = (ConcretePrototype2) client3.operation();
        System.out.println(clone3.getValue());
    }
}
