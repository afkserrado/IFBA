package br.ifba.l3q2.order;
import java.util.List;

import br.ifba.l3q2.Customer;
import br.ifba.l3q2.Product;
import br.ifba.l3q2.processingStrategy.IProcessingStrategy;

// Abstract class: it can't be instantiated
public abstract class IOrder {
    
    //
    // Fields
    //
    private final List<Product> items; 
    private final Customer customer;

    //
    // Constructor
    //
    public IOrder(List<Product> items, Customer customer) {
        this.items = items;
        this.customer = customer;
    }

    //
    // Methods
    //
    // Getters
    public List<Product> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Processing strategy type
    public abstract IProcessingStrategy defaultProcessingStrategy();
}
