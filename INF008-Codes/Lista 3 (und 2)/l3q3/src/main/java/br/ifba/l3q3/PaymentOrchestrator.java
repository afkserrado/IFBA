package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.*;
import br.ifba.l3q3.paymentProcessor.*;
import br.ifba.l3q3.riskAnalyzer.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class PaymentOrchestrator {
    
    private final Map<String, IPaymentProcessor> paymentProcessors = new HashMap<>();
    private final Map<String, IRiskAnalyzer> riskAnalyzers = new HashMap<>();

    // Default constructor

    // Public methods
    //
    // Registers a payment processor
    public void registerPaymentProcessor(IPaymentProcessor paymentProcessor) {   
        String processorName = paymentProcessor.getClass().getSimpleName();

        if(!processorName.contains("Processor")) {
            throw new IllegalArgumentException("Invalid processor: class name must contains 'Processor'.");
        }

        processorName = processorName.replaceAll("Processor","").concat("Payment");
        
        paymentProcessors.put(processorName, paymentProcessor);
    }

    // Registers a risk analyzer
    public void registerRiskAnalyzer(String risk, IRiskAnalyzer riskAnalyzer) {
        riskAnalyzers.put(risk, riskAnalyzer);
    }

    // Processes a single payment
    public PaymentResult processPayment(IPayment payment, String risk) {

        IRiskAnalyzer riskAnalyzer = riskAnalyzers.get(risk);
        if(riskAnalyzer == null) {
            throw new IllegalArgumentException("Invalid risk: there's no risk analyzer compatible with the selected risk.");
        }

        boolean riskAnalyzed = riskAnalyzer.analyzeRisk(payment);

        String paymentName = payment.getClass().getSimpleName();

        if(!paymentName.contains("Payment")) {
            throw new IllegalArgumentException("Invalid payment: class name must contains 'Payment'.");
        }

        IPaymentProcessor paymentProcessor = paymentProcessors.get(paymentName);
        if(paymentProcessor == null) {
            throw new IllegalArgumentException("Invalid payment: there's no payment processor compatible with the selected payment.");
        }

        boolean paymentProcessed = paymentProcessor.processor(payment, riskAnalyzed);

        return new PaymentResult(payment, paymentProcessed);
    }

    // Processes a batch of payments
    public List<PaymentResult> processBatch(List<IPayment> batchPayments, String risk) {
        List<PaymentResult> batchResults = new LinkedList<>();
        
        for(IPayment payment : batchPayments) {
            PaymentResult paymentResult = processPayment(payment, risk);
            batchResults.add(paymentResult);
        }

        return batchResults;
    }
}
