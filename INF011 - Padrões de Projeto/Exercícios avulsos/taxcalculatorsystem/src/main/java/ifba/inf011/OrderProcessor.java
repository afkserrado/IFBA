package ifba.inf011;

import ifba.inf011.Interfaces.TaxCalculator;

public class OrderProcessor {
    
    TaxCalculator taxCalculator;

    public OrderProcessor(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void processOrder(double amount) {
        
        double tax = taxCalculator.calculateTax(amount);
        double total = amount + tax;
        String region = taxCalculator.getRegion();

        System.out.println(region + " - Subtotal: $" + amount + ", Tax: $" + tax + ", Total: $" + total);
    }
}
