package br.ifba.l3q2.order;
import java.util.List;

import br.ifba.l3q2.Customer;
import br.ifba.l3q2.Product;
import br.ifba.l3q2.processingStrategy.IProcessingStrategy;
import br.ifba.l3q2.processingStrategy.InternationalProcessingStrategy;

public class InternationalOrder extends IOrder {

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
