package br.ifba.l3q2;

public class ProcessingResult {
    
    //
    // Fields
    //
    private String strategyProcessed;
    private double shippingCalculated;

    //
    // Constructor
    //
    public ProcessingResult(String strategyProcessed, double shippingCalculated) {
        this.strategyProcessed = strategyProcessed;
        this.shippingCalculated = shippingCalculated;
    }
}
