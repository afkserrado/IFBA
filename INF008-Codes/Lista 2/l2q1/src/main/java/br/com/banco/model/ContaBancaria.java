package br.com.banco.model;

public class ContaBancaria {
    private String numero;
    private double saldo;
    private String tipo; // "CORRENTE" ou "POUPANCA"

    // Construtor 1
    public ContaBancaria(String numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = 0.0;
    }

    // Construtor 2
    public ContaBancaria(String numero, String tipo, double saldoInicial) {
        // TODO: Implementar chamada a outro construtor
        // TODO: Validar saldoInicial >= 0
        this(numero, tipo); // Deve ser a primeira linha do construtor
        if (saldoInicial >= 0) {
            saldo = saldoInicial; // 'this' não é obrigatório aqui            
        }
        else {
            saldo = 0.0;
        }
    }

    // TODO: Criar métodos sobrecarregados para depositar
    // depositar(double valor) e depositar(double valor, String descricao)
    private boolean validarValor(double valor) {
        return valor > 0;
    }

    public void depositar(double valor) {
        if (validarValor(valor)) {
            saldo += valor;
            System.out.println("Depósito realizado: R$ " + valor);
        }
        else {
            System.out.println("Valor inválido.");
        }
    }

    public void depositar(double valor, String descricao) {
        depositar(valor);
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String tipo() {
        return tipo;
    }

    // TODO: Criar getters APENAS para os atributos necessários
}
