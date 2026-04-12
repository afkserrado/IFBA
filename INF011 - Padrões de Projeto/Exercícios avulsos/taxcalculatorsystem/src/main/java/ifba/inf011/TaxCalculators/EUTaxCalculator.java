package ifba.inf011.TaxCalculators;

import ifba.inf011.Interfaces.TaxCalculator;

public class EUTaxCalculator implements TaxCalculator {
    
    @Override
    public double calculateTax(double amount) {
        return amount * 0.20;
    }

    @Override
    public String getRegion() {
        return "EU";
    }
}
