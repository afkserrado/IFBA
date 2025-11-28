package br.ifba.l3q3.riskAnalyzer;

// Imports
import br.ifba.l3q3.payment.IPayment;

public class MediumRiskAnalyzer implements IRiskAnalyzer {
    
    // Default constructor

    // Public methods
    //
    // Analyze the risk of the transaction based on its value
    @Override
    public boolean analyzeRisk(IPayment payment) {
        double value = payment.getValue();
        return value > 1000; // Risky if true
    }
}
