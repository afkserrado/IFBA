/*
Questão 3: Sistema de Processamento de Pagamentos para E-commerce 

Implemente um sistema de pagamentos que suporte múltiplas formas de pagamento através de hierarquias paralelas. O sistema deve utilizar um registro dinâmico de processadores de pagamento e analisadores de risco. O código cliente deve demonstrar o uso de classes concretas específicas. 
*/

package br.ifba.l3q3;

// Imports
import java.util.Arrays;
import java.util.List;

import br.ifba.l3q3.payment.CreditCardPayment;
import br.ifba.l3q3.payment.CryptoPayment;
import br.ifba.l3q3.payment.IPayment;
import br.ifba.l3q3.payment.PIXPayment;
import br.ifba.l3q3.paymentProcessor.CreditCardProcessor;
import br.ifba.l3q3.paymentProcessor.CryptoProcessor;
import br.ifba.l3q3.paymentProcessor.PIXProcessor;
import br.ifba.l3q3.riskAnalyzer.HighRiskAnalyzer;
import br.ifba.l3q3.riskAnalyzer.LowRiskAnalyzer;
import br.ifba.l3q3.riskAnalyzer.MediumRiskAnalyzer;

public class ECommerceApp {
    public static void main(String[] args) {
        PaymentOrchestrator orchestrator = new PaymentOrchestrator();

        // Registro de processadores
        // cada um sabe quais tipos de pagamento suporta
        orchestrator.registerPaymentProcessor(new CreditCardProcessor());
        orchestrator.registerPaymentProcessor(new PIXProcessor());
        orchestrator.registerPaymentProcessor(new CryptoProcessor());

        // Registro de analisadores de risco
        orchestrator.registerRiskAnalyzer("high", new HighRiskAnalyzer());
        orchestrator.registerRiskAnalyzer("low", new LowRiskAnalyzer());
        orchestrator.registerRiskAnalyzer("medium", new MediumRiskAnalyzer());

        // Processamento - o próprio PAGAMENTO determina qual processador usar
        // IMPOSSÍVEL usar CreditCardPayment com PIXProcessor
        PaymentResult result1 = orchestrator.processPayment(
            new CreditCardPayment(150.0, "4111111111111111", "12/25"),
            "high"  // Apenas chave do analisador de risco
        );

        PaymentResult result2 = orchestrator.processPayment(
            new PIXPayment(200.0, "123e4567-e89b-12d3-a456-426614174000"),
            "low"   // Apenas chave do analisador de risco
        );

        PaymentResult result3 = orchestrator.processPayment(
            new CryptoPayment(300.0, "0x742d35Cc6634C0532925a3b8D", "ETH"),
            "medium" // Apenas chave do analisador de risco
        );

        // Processamento em lote - type safe
        List<IPayment> batchPayments = Arrays.asList(
            new CreditCardPayment(100.0, "4222222222222222", "06/24"),
            new PIXPayment(50.0, "123e4567-e89b-12d3-a456-426614174001"),
            new CryptoPayment(75.0, "0x842d35Cc6634C0532925a3b8E", "BTC")
        );

        System.out.println("\nBatch process: ");
        List<PaymentResult> batchResults = orchestrator.processBatch(
            batchPayments,
            "low"  // Analisador de risco para todo o lote
        );

        // Adição de novo tipo de pagamento SEM risco de combinação inválida
        // orchestrator.registerPaymentProcessor(new DigitalWalletProcessor());
        // new DigitalWalletPayment(...)
        //           só funcionará com DigitalWalletProcessor
    }
}

