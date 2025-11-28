package br.ifba.l3q3.riskAnalyzer;

// Imports
import br.ifba.l3q3.payment.IPayment;

public class HighRiskAnalyzer implements IRiskAnalyzer {
    
    // Default constructor

    // Public methods
    //
    // Analyze the risk of the transaction based on its value
    @Override
    public boolean analyzeRisk(IPayment payment) {
        double value = payment.getValue();
        return !(value > 5000); // Risky if false
    }
}
