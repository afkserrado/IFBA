package br.ifba.l3q2;
import java.util.List;

// Abstract class: it can't be instantiated
public abstract class Order {
    
    //
    // Fields
    //
    private final List<Product> items; 
    private final Customer customer;

    //
    // Constructor
    //
    public Order(List<Product> items, Customer customer) {
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
