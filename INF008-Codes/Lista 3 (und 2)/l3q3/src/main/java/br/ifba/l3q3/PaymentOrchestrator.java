package br.ifba.l3q3;

// Imports
import br.ifba.l3q3.payment.*;
import br.ifba.l3q3.paymentProcessor.*;
import br.ifba.l3q3.riskAnalyzer.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class PaymentOrchestrator {
    
    private final List<IPaymentProcessor> paymentProcessors = new ArrayList<>();
    private final Map<String, IRiskAnalyzer> riskAnalyzers = new HashMap<>();

    // Default constructor

    // Public methods
    //
    // Registers a payment processor
    public void registerPaymentProcessor(IPaymentProcessor paymentProcessor) {   
        if(!paymentProcessors.contains(paymentProcessor)) {
            paymentProcessors.add(paymentProcessor);
        }
    }

    // Registers a risk analyzer
    public void registerRiskAnalyzer(String risk, IRiskAnalyzer riskAnalyzer) {
        riskAnalyzers.put(risk, riskAnalyzer);
    }

    // Processes a single payment
    public PaymentResult processPayment(IPayment payment, String risk) {

        IRiskAnalyzer riskAnalyzer = riskAnalyzers.get(risk);
        boolean riskAnalyzed = false;

        if(riskAnalyzer != null) {
            riskAnalyzed = riskAnalyzer.analyzeRisk(payment);
        }

        String paymentProcessed = "Payment unprocessed";
        for(IPaymentProcessor paymentProcessor : paymentProcessors) {
            if(paymentProcessor.getPaymentType().equals(payment.getPaymentType())) {
                paymentProcessed = paymentProcessor.processor(payment, riskAnalyzed);
                break;
            }
        }

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
