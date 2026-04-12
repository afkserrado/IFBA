package ifba.inf011;

import ifba.inf011.TaxCalculators.EUTaxCalculator;
import ifba.inf011.TaxCalculators.UKTaxCalculator;
import ifba.inf011.TaxCalculators.USTaxCalculator;

public class Main {
    public static void main(String[] args) {
        
        OrderProcessor usProcessor = new OrderProcessor(new USTaxCalculator());
        usProcessor.processOrder(100.0);

        OrderProcessor euProcessor = new OrderProcessor(new EUTaxCalculator());
        euProcessor.processOrder(100.0);

        OrderProcessor ukProcessor = new OrderProcessor(new UKTaxCalculator());
        ukProcessor.processOrder(100.0);
    }
}