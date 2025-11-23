package br.ifba.l3q2;

public class OrderProcessor {

    public ProcessingResult processOrder(Order order, IProcessingStrategy strategy, IShippingCalculator calculator) {
        String strategyProcessed = strategy.processStrategy(order);
        double shippingCalculated = calculator.calculateShipping(order);

        return new ProcessingResult(strategyProcessed, shippingCalculated);
    }
}
