package br.ifba.l3q3.payment;

public abstract class Payment {

    private final double value;

    // Constructor
    public Payment(double value) {
        if(value <= 0) {
            throw new IllegalArgumentException("Invalid value: must be greater than zero.");
        }

        this.value = value;
    }

    // Getters
    //
    // Gets value
    public double getValue() {
        return value;
    }
}
