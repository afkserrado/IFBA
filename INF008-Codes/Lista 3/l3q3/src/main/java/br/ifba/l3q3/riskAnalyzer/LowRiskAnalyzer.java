package br.ifba.l3q3.riskAnalyzer;

// Imports
import br.ifba.l3q3.payment.IPayment;

public class LowRiskAnalyzer implements IRiskAnalyzer {
    
    // Default constructor

    // Public methods
    //
    // Analyzes the risk of the transaction based on its value
    @Override
    public boolean analyzeRisk(IPayment payment) {
        double value = payment.getValue();
        return !(value > 100); // Risky if false
    }
}
