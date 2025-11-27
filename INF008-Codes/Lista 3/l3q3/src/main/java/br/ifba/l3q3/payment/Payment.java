package br.ifba.l3q3.payment;

public abstract class Payment {

    private final double value;

    // Constructor
    public Payment(double value) {
        this.value = value;
    }

    // Getters
    public double getValue() {
        return value;
    }
}
