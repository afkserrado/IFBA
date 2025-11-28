package br.ifba.l3q3.payment;

public class CreditCardPayment extends Payment {
    
    private final String cardNumber;
    private final String expirationDate;

    // Constructor
    public CreditCardPayment(double value, String cardNumber, String expirationDate) {
        super(value);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }

    // Getters
    //
    // Gets card number
    public String getCardNumber() {
        return cardNumber;
    }

    // Gets expiration date
    public String getExpirationDate() {
        return expirationDate;
    }
}
