package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.*;
import br.ifba.l3q3.paymentProcessor.*;
import br.ifba.l3q3.riskAnalyzer.*;
import java.util.Map;
import java.util.HashMap;

public class PaymentOrchestrator {
    
    private Map<String, IPaymentProcessor> paymentProcessors = new HashMap<>();
    private Map<String, IRiskAnalyzer> riskAnalyzers = new HashMap<>();

    // Default constructor

    // Public methods
    //
    // Register a payment processor
    public void registerPaymentProcessor(IPaymentProcessor paymentProcessor) {   
        String processorName = paymentProcessor.getClass().getSimpleName();

        if(!processorName.contains("Processor")) {
            throw new IllegalArgumentException("Invalid processor: class name must contains 'Processor'.");
        }

        processorName = processorName.replaceAll("Processor","").concat("Payment");
        
        paymentProcessors.put(processorName, paymentProcessor);
    }

    // Register a risk analyzer
    public void registerRiskAnalyzer(String risk, IRiskAnalyzer riskAnalyzer) {
        riskAnalyzers.put(risk, riskAnalyzer);
    }

    // Process a payment
    public PaymentResult processPayment(IPayment payment, String risk) {

        IRiskAnalyzer riskAnalyzer = riskAnalyzers.get(risk);
        boolean riskAnalyzed = riskAnalyzer.analyzeRisk(payment);

        String paymentName = payment.getClass().getSimpleName();

        if(!paymentName.contains("Payment")) {
            throw new IllegalArgumentException("Invalid payment: class name must contains 'Payment'.");
        }

        IPaymentProcessor paymentProcessor = paymentProcessors.get(paymentName);
        boolean paymentProcessed = paymentProcessor.processor(payment, riskAnalyzed);

        return new PaymentResult(payment, paymentProcessed);
    }
}
