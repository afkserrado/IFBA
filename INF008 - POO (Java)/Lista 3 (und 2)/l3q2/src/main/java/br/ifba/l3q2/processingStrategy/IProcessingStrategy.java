package br.ifba.l3q2.processingStrategy;

import br.ifba.l3q2.order.IOrder;

public interface IProcessingStrategy {
    
    //
    // Methods
    //
    // Public and abstract by default
    String processStrategy(IOrder order);

    // Default method: provides a reusable implementation for all implementing classes
    default String extractPrefix() {
        String className = getClass().getSimpleName();
        int i;

        for(i = 1; i < className.length(); i++) {
            if(Character.isUpperCase(className.charAt(i))) {
                break;
            }
        }

        // Returns the prefix (e.g., Express, International, Standard)
        return className.substring(0, i);
    }
}
