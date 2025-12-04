package br.ifba.l3q3.riskAnalyzer;

// Imports
import br.ifba.l3q3.payment.*;

public interface IRiskAnalyzer {
    
    // Analyzes the payment and returns false if risky
    public boolean analyzeRisk(IPayment payment);
}
