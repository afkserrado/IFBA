package br.ifba.l3q3.payment;

import br.ifba.l3q3.paymentProcessor.CreditCardProcessor;

public class CreditCardPayment extends IPayment {
    
    private final double value;
    private final String cardNumber;
    private final String expirationDate;

    // Constructor
    public CreditCardPayment(double value, String cardNumber, String expirationDate) {
        this.value = value;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.processor = new CreditCardProcessor();
    }

    @Override
    public boolean processPayment() {
        System.out.println("CreditCardPayment...");
        processor.process();

        return true;
    }

    // Getters
    //
    // Gets value
    @Override
    public double getValue() {
        return value;
    }

    // Gets card number
    public String getCardNumber() {
        return cardNumber;
    }

    // Gets expiration date
    public String getExpirationDate() {
        return expirationDate;
    }

    public boolean validateData() {
        return cardNumber != null && !cardNumber.isEmpty();
    }
}
