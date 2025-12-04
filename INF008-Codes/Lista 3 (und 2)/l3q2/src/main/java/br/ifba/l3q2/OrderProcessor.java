package br.ifba.l3q2;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;

public class OrderProcessor {

    //
    // Constructor
    //
    // Default

    //
    // Methods
    //
    // Processes a single order
    public ProcessingResult processOrder(Order order, IProcessingStrategy strategy, IShippingCalculator calculator) {
        
        String strategyProcessed = strategy.processStrategy(order);
        double shippingCalculated = calculator.calculateShipping(order);

        return new ProcessingResult(strategyProcessed, shippingCalculated);
    }

    // Processes a batch of orders
    public List<ProcessingResult> processOrderBatch(List<Order> orderBatch, Map<Order, IProcessingStrategy> strategyMap, IShippingCalculator shippingCalculator) {
        
        List<ProcessingResult> batchResults = new ArrayList<>();

        Iterator<Order> it = orderBatch.iterator();
        while(it.hasNext()) {
            Order order = it.next();
            IProcessingStrategy strategy = strategyMap.getOrDefault(order, order.defaultProcessingStrategy());
            batchResults.add(processOrder(order, strategy, shippingCalculator));
        }

        return batchResults;
    }
}
