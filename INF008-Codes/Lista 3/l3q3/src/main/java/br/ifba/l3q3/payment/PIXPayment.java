package br.ifba.l3q3.payment;

public class PIXPayment extends Payment {
    
    private final String key;

    // Constructor
    public PIXPayment(double value, String key) {
        super(value);
        this.key = key;        
    }

    // Getters
    //
    // Gets key
    public String getKey() {
        return key;
    }
}
