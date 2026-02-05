package br.ifba.l3q3.payment;

import br.ifba.l3q3.paymentProcessor.IPaymentProcessor;

public abstract class IPayment {

    protected IPaymentProcessor processor;

    public abstract boolean processPayment();

    // Getters
    //
    // Gets value
    public abstract double getValue();
}
