package br.com.banco.model;

public class Main {
    public static void main(String[] args) {
        // Criando conta corrente com saldo inicial
        ContaBancaria conta1 = new ContaBancaria("001", "CORRENTE", 500.0);
        System.out.println("Conta criada: " + conta1.getNumero() + " - Tipo: " + conta1.tipo());
        System.out.println("Saldo inicial: R$ " + conta1.getSaldo());
        System.out.println();

        // Depositando valores
        conta1.depositar(250.0);
        conta1.depositar(100.0, "Depósito de aniversário");

        System.out.println("Saldo atual: R$ " + conta1.getSaldo());
        System.out.println();

        // Criando conta poupança sem saldo inicial
        ContaBancaria conta2 = new ContaBancaria("002", "POUPANCA");
        System.out.println("Conta criada: " + conta2.getNumero() + " - Tipo: " + conta2.tipo());
        System.out.println("Saldo inicial: R$ " + conta2.getSaldo());
        conta2.depositar(300.0, "Depósito mensal");
        System.out.println("Saldo atual: R$ " + conta2.getSaldo());
    }
}
