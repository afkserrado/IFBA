package br.ifba.l3q2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.ifba.l3q2.order.IOrder;
import br.ifba.l3q2.processingStrategy.IProcessingStrategy;
import br.ifba.l3q2.shippingCalculator.IShippingCalculator;

public class OrderProcessor {

    //
    // Constructor
    //
    // Default

    //
    // Methods
    //
    // Processes a single order
    public ProcessingResult processOrder(IOrder order, IProcessingStrategy strategy, IShippingCalculator calculator) {
        
        String strategyProcessed = strategy.processStrategy(order);
        double shippingCalculated = calculator.calculateShipping(order);

        return new ProcessingResult(strategyProcessed, shippingCalculated);
    }

    // Processes a batch of orders
    public List<ProcessingResult> processOrderBatch(List<IOrder> orderBatch, Map<IOrder, IProcessingStrategy> strategyMap, IShippingCalculator shippingCalculator) {
        
        List<ProcessingResult> batchResults = new ArrayList<>();

        Iterator<IOrder> it = orderBatch.iterator();
        while(it.hasNext()) {
            IOrder order = it.next();
            IProcessingStrategy strategy = strategyMap.get(order);
            batchResults.add(processOrder(order, strategy, shippingCalculator));
        }

        return batchResults;
    }
}
