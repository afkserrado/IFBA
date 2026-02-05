package br.ifba.l3q2.shippingCalculator;

import br.ifba.l3q2.order.IOrder;

public class DomesticShippingCalculator implements IShippingCalculator {
    //
    // Fields
    //
    private final double shipping = 10.0;
    
    //
    // Constructor
    //
    // Default

    //
    // Methods
    //

    // Processes the shipping by using this strategy
    @Override
    public double calculateShipping(IOrder order) {
        return calculateProducts(order) * shipping;
    }
}
