package br.ifba.l3q2;

public class Product {
    
    //
    // Fields
    //
    private String id;
    private String name;
    private int quantity;
    private double price;

    //
    // Constructor
    //
    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    //
    // Methods
    //
    // Getters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
