package com.ifba.l1q2;

public class Main {
    public static void main(String[] args) {
        // Criando caixas com diferentes construtores
        CashRegister caixa1 = new CashRegister("CAIXA01");
        CashRegister caixa2 = new CashRegister("CAIXA02", 100.0);
        CashRegister caixa3 = new CashRegister("CAIXA03", 200.0, 5);

        System.out.println("=== Testando caixa1 ===");
        caixa1.processPayment(50.0); // pagamento double
        caixa1.processPayment(25);   // pagamento int
        caixa1.processPayment(30.0, "Compra de mercadoria"); // pagamento com descrição
        caixa1.processRefund(20.0);  // reembolso válido
        caixa1.processRefund(200.0); // reembolso inválido, saldo insuficiente
        caixa1.getDailyReport();     // relatório diário

        System.out.println("\n=== Testando caixa2 ===");
        caixa2.processPayment(75.5);
        caixa2.processRefund(50.0);
        caixa2.getDailyReport();

        System.out.println("\n=== Testando caixa3 ===");
        caixa3.processPayment(10);
        caixa3.processPayment(5.5, "Serviço adicional");
        caixa3.processRefund(100.0);
        caixa3.getDailyReport();
    }
}