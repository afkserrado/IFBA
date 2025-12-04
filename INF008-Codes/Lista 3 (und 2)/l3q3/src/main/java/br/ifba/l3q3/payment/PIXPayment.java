package br.ifba.l3q3.payment;

public class PIXPayment implements IPayment {
    
    private final double value;
    private final String key;

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
