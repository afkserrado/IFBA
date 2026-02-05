package br.ifba.l3q3;

// Imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ifba.l3q3.payment.IPayment;
import br.ifba.l3q3.paymentProcessor.IPaymentProcessor;
import br.ifba.l3q3.riskAnalyzer.IRiskAnalyzer;

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

        if(riskAnalyzers.containsKey(risk)) {

            if(riskAnalyzers.get(risk).analyzeRisk(payment)) {
                payment.processPayment();
                return new PaymentResult(payment);
            }
            else {
                System.out.println("Payment too risky!");
            }
        }
        else {
            System.out.println("Risk analyzer not found!");
        }
    
        return null;
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
