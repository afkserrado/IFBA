package br.ifba.l3q2;
import java.util.List;

// Abastract class: it can't be instantieded
public abstract class Order {
    
    //
    // Fields
    //
    private List<Product> items; 
    private String customer;

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
}
