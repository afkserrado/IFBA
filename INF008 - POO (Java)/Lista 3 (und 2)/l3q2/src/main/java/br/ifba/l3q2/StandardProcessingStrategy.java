package br.ifba.l3q2;

public class StandardProcessingStrategy implements IProcessingStrategy {
    
    //
    // Fields
    //
    // Prefix of the class name (e.g., Express, International, Standard)
    private final String prefix;
    
    //
    // Constructor
    //
    public StandardProcessingStrategy() {
        // Extracts the prefix of the instance
        prefix = extractPrefix();
    }

    //
    // Methods
    //

    // Processes the order by using this strategy
    @Override
    public String processStrategy(Order order) {
        return "Customer: " + order.getCustomer() + " | Result: " + prefix + " process strategy applied.";
    }
}