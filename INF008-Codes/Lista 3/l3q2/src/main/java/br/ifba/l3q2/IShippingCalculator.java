package br.ifba.l3q2;

public interface IShippingCalculator {
    
    //
    // Methods
    //
    // Public and abstract by default
    double calculateShipping(Order order);

    // Gets the total quantity of products of an order
    default int calculateProducts(Order order) {
        int products = 0;
        
        for (Product item : order.getItems()) {
            products += item.getQuantity();
        }

        return products;
    }
}
