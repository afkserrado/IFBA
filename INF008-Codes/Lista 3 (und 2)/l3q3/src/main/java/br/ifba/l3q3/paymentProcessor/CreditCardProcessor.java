package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public class CreditCardProcessor implements IPaymentProcessor {
    
    private final String paymentType = "credit card";

    // Default constructor

    // Public methods
    //
    // Processes payment
    @Override
    public String processor(IPayment payment, boolean riskAnalyzed) {
        if(!payment.getPaymentType().equals(paymentType)) {
            return "Invalid payment type for CreditCardProcessor";
        }

        if(!riskAnalyzed) {
            return "Credit card process fail due to risk";
        }
        
        return "Credit card payment processed";
    }

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }
}
