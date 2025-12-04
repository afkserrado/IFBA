package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public interface IPaymentProcessor {
    
    // Processes the payment and returns true if is ok
    public boolean processor(IPayment payment, boolean riskAnalyzed);

    // Implementar futuramente
    //Class<? extends IPayment> getAcceptedPaymentType();
}
