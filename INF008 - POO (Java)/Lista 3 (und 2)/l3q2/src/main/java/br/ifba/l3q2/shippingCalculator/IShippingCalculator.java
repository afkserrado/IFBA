package br.ifba.l3q2.shippingCalculator;

import br.ifba.l3q2.Product;
import br.ifba.l3q2.order.IOrder;

public interface IShippingCalculator {
    
    //
    // Methods
    //
    // Public and abstract by default
    double calculateShipping(IOrder order);

    // Gets the total quantity of products of an order
    default int calculateProducts(IOrder order) {
        int products = 0;
        
        for (Product item : order.getItems()) {
            products += item.getQuantity();
        }

        return products;
    }
}
