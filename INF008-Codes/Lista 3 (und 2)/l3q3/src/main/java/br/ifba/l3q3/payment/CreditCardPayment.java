package br.ifba.l3q3.payment;
import java.time.LocalDate;

public class CreditCardPayment implements IPayment {
    
    private final String paymentType = "credit card";
    private final double value;
    private final String cardNumber;
    private final String expirationDate;

    // Constructor
    public CreditCardPayment(double value, String cardNumber, String expirationDate) {
        this.value = value;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
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

    // Gets payment type
    @Override
    public String getPaymentType() {
        return paymentType;
    }

    public boolean validateData() {
        return cardNumber != null && !cardNumber.isEmpty();
    }
}
