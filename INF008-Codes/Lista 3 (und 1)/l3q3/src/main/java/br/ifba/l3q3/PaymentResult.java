package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.*;

public class PaymentResult {

    private IPayment payment;
    private boolean result;
    
    // Constructor
    public PaymentResult(IPayment payment, boolean result) {
        this.payment = payment;
        this.result = result;
    }
}
