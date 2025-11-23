package br.ifba.l3q2;
import java.util.List;

public class ExpressOrder extends Order {

    //
    // Constructor
    //
    public ExpressOrder(List<Product> items, String customer) {
        super(items, customer);
    }

    //
    // Methods
    //
    // Returns the default processing strategy for this type of order
    @Override
    public IProcessingStrategy defaultProcessingStrategy() {
        return new ExpressProcessingStrategy();
    }
}
