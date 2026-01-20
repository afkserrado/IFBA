package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public class CryptoProcessor implements IPaymentProcessor {
 
    private final String paymentType = "crypto";

    // Default constructor

    // Public methods
    //
    // Processes payment
    @Override
    public String processor(IPayment payment, boolean riskAnalyzed) {
        if(!payment.getPaymentType().equals(paymentType)) {
            return "Invalid payment type for CryptoProcessor";
        }

        if(!riskAnalyzed) {
            return "Crypto process fail due to risk";
        }
        
        return "Crypto payment processed";
    }

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }
}
