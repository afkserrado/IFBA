package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public class PIXProcessor implements IPaymentProcessor {

    private final String paymentType = "pix";

    // Default constructor

    // Public methods
    //
    // Processes payment
    @Override
    public String processor(IPayment payment, boolean riskAnalyzed) {
        if(!payment.getPaymentType().equals(paymentType)) {
            return "Invalid payment type for PIXProcessor";
        }

        if(!riskAnalyzed) {
            return "PIX process fail due to risk";
        }
        
        return "PIX payment processed";
    }

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }
}
