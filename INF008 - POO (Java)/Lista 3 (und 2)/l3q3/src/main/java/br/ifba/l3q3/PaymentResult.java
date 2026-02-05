package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.IPayment;

public class PaymentResult {

    private IPayment payment;

    // Constructor
    public PaymentResult(IPayment payment) {
        this.payment = payment;
    }

    // Getters
    public IPayment getPayment() { return payment; }
}
