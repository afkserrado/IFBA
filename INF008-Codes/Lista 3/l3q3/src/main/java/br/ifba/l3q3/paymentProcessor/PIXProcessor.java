package br.ifba.l3q3.paymentProcessor;

// Imports
import br.ifba.l3q3.payment.*;

public class PIXProcessor implements IPaymentProcessor {
    
    // Default constructor

    // Public methods
    //
    // Process payment
    @Override
    public boolean processor(IPayment payment, boolean risk) {
        return payment instanceof PIXPayment || risk == true;
    }
}
