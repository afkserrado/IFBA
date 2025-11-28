package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public class CryptoProcessor implements IPaymentProcessor {
 
    // Default constructor

    // Public methods
    //
    // Processes payment
    @Override
    public boolean processor(IPayment payment, boolean riskAnalyzed) {
        // Returns true if 'payment' matches the expected type and is considered safe (riskAnalyzed = true)
        return (payment instanceof CryptoProcessor && riskAnalyzed);
    }
}
