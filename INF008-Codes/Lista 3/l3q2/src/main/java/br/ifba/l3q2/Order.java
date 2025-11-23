package br.ifba.l3q2;
import java.util.Map;

// Abastract class: it can't be instantieded
public abstract class Order {
    
    //
    // Fields
    //
    // Key: product or productID; Value: quantity of items
    private Map<String, Integer> items; 
    private String customer;

    //
    // Constructor
    //
    public Order(Map<String, Integer> items, String customer) {
        this.items = items;
        this.customer = customer;
    }

    //
    // Methods: getters
    //
    public Map<String, Integer> getItems() {
        return items;
    }

    public String getCustomer() {
        return customer;
    }
}
