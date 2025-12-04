package br.ifba.l3q3.payment;

import br.ifba.l3q3.paymentProcessor.*;

public class PIXPayment implements IPayment {
    
    private final double value;
    private final String key;
    //private final IPaymentProcessor;

    // Constructor
    public PIXPayment(double value, String key) {
        if(value <= 0) {
            throw new IllegalArgumentException("Invalid value: must be greater than zero.");
        }

        this.value = value;
        this.key = key;        
    }

    // Getters
    //
    // Gets value
    @Override
    public double getValue() {
        return value;
    }

    // Gets key
    public String getKey() {
        return key;
    }
}
