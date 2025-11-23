package br.ifba.l3q2;
import java.util.List;

public class InternationalOrder extends Order {

    //
    // Constructor
    //
    public InternationalOrder(List<Product> items, Customer customer) {
        super(items, customer);
    }

    //
    // Methods
    //
    // Returns the default processing strategy for this type of order
    @Override
    public IProcessingStrategy defaultProcessingStrategy() {
        return new InternationalProcessingStrategy();
    }
}
