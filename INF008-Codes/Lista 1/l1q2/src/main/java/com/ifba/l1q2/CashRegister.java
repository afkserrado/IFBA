package com.ifba.l1q2;

class CashRegister {
    private double currentBalance = 0;
    private int transactionCount = 0;
    private String registerId;

    // Construtores sobrecarregados
    public CashRegister(String registerId, double currentBalance, int transactionCount) {
        this.registerId = registerId;
        this.currentBalance = currentBalance;
        this.transactionCount = transactionCount;
    }

    public CashRegister(String registerId, double currentBalance) {
        this.registerId = registerId;
        this.currentBalance = currentBalance;
    }

    public CashRegister(String registerId) {
        this.registerId = registerId;
    }

    // Processa um pagamento
    public void processPayment(double amount) {
        this.currentBalance += amount;
        this.transactionCount++;
    }

    // Processa um pagamento
    public void processPayment(double amount, String description) {
        this.currentBalance += amount;
        this.transactionCount++;
        System.out.println("Pagamento: " + description + " | Valor: " + amount);
    }

    // Processa um pagamento
    public void processPayment(int amount) {
        if(amount > 0) {
            this.currentBalance += amount;
            this.transactionCount++;
        }
    }

    // Processa um reembolso
    public void processRefund(double amount) {
        if(currentBalance >= amount) {
            this.currentBalance -= amount;
            transactionCount++;
        }
        else {
            System.out.println("Saldo insuficiente para reembolso.");
        }
    }

    public void getDailyReport() {
        System.out.println("Caixa: " + registerId);
        System.out.println("Saldo atual: " + currentBalance);
        System.out.println("Transações realizadas: " + transactionCount);
    }
}
