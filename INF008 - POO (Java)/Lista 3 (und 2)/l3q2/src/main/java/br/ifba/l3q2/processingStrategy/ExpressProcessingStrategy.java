package br.ifba.l3q2.processingStrategy;

import br.ifba.l3q2.order.IOrder;

public class ExpressProcessingStrategy implements IProcessingStrategy {
    
    //
    // Fields
    //
    // Prefix of the class name (e.g., Express, International, Standard)
    private final String prefix;
    
    //
    // Constructor
    //
    public ExpressProcessingStrategy() {
        // Extracts the prefix of the instance
        prefix = extractPrefix();
    }

    //
    // Methods
    //

    // Processes the order by using this strategy
    @Override
    public String processStrategy(IOrder order) {
        return "Customer: " + order.getCustomer() + " | Result: " + prefix + " process strategy applied.";
    }
}
