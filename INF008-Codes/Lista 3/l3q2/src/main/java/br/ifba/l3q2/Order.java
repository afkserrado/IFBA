package br.ifba.l3q2;
import java.util.List;

// Abstract class: it can't be instantiated
public abstract class Order {
    
    //
    // Fields
    //
    private final List<Product> items; 
    private final String customer;

    //
    // Constructor
    //
    public Order(List<Product> items, String customer) {
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

    public String getCustomer() {
        return customer;
    }

    // Processing strategy type
    public abstract IProcessingStrategy defaultProcessingStrategy();
}
