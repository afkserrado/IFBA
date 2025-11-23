package br.ifba.l3q2;

public class DomesticShippingCalculator implements IShippingCalculator {
    //
    // Fields
    //
    private final double shippingTax = 10.0;
    
    //
    // Constructor
    //
    // Default

    //
    // Methods
    //

    // Processes the shipping by using this strategy
    @Override
    public double calculateShipping(Order order) {
        return calculateProducts(order) * shippingTax;
    }
}
