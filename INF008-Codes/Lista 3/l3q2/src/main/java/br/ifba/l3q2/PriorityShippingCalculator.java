package br.ifba.l3q2;

public class PriorityShippingCalculator implements IShippingCalculator {
    //
    // Fields
    //
    private final double shipping = 10.0;
    private final double priorityFee = 50.0;
    
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
        return calculateProducts(order) * shipping + priorityFee;
    }
}
