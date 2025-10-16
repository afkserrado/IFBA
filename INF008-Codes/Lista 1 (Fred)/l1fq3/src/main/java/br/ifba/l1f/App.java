package br.ifba.l1f;

public class App {
    public static void main(String[] args) {
        // Criando números complexos
        NumeroComplexo n1 = new NumeroComplexo(3, 4);
        NumeroComplexo n2 = new NumeroComplexo(1, -2);

        // Exibir os números
        System.out.println("Número 1: " + n1.ncToString());
        System.out.println("Número 2: " + n2.ncToString());

        // Somar
        NumeroComplexo soma = n1.somar(n2);
        System.out.println("Soma: " + soma.ncToString());

        // Subtrair
        NumeroComplexo subtracao = n1.subtrair(n2);
        System.out.println("Subtração: " + subtracao.ncToString());

        // Multiplicar
        NumeroComplexo multiplicacao = n1.multiplicar(n2);
        System.out.println("Multiplicação: " + multiplicacao.ncToString());

        // Dividir
        NumeroComplexo divisao = n1.dividir(n2);
        System.out.println("Divisão: " + divisao.ncToString());

        // Comparar
        boolean comp1 = n1.comparar(n2);
        boolean comp2 = n1.comparar(new NumeroComplexo(3, 4));
        System.out.println("n1 == n2? " + comp1);
        System.out.println("n1 == (3+4i)? " + comp2);

        // Módulo
        System.out.println("Módulo de n1: " + n1.modulo());
        System.out.println("Módulo de n2: " + n2.modulo());
    }
}
