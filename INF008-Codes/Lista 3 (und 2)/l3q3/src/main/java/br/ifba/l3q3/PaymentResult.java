package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.*;

public class PaymentResult {

    private IPayment payment;
    private String result;
    
    // Constructor
    public PaymentResult(IPayment payment, String result) {
        this.payment = payment;
        this.result = result;
    }

    // Getters
    public IPayment getPayment() { return payment; }
    public String getResult() { return result; }
}
