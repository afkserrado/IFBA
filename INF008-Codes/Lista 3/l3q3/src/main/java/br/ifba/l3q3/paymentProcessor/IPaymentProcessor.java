package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public interface IPaymentProcessor {
    
    public boolean processor(IPayment payment, boolean risk);
}
