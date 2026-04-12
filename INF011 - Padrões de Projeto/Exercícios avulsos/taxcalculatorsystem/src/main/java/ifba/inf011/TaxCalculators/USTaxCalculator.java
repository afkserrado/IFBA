package ifba.inf011.TaxCalculators;

import ifba.inf011.Interfaces.TaxCalculator;

public class USTaxCalculator implements TaxCalculator {
    
    @Override
    public double calculateTax(double amount) {
        return amount * 0.10;
    }

    @Override
    public String getRegion() {
        return "US";
    }
}
