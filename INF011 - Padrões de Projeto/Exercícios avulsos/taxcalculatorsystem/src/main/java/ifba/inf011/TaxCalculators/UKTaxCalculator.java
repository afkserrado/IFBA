package ifba.inf011.TaxCalculators;

import ifba.inf011.Interfaces.TaxCalculator;

public class UKTaxCalculator implements TaxCalculator {
    
    @Override
    public double calculateTax(double amount) {
        return amount * 0.15;
    }

    @Override
    public String getRegion() {
        return "UK";
    }
}
