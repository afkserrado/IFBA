package com.ifba.l1q3;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Teste com construtor padrão (precisão 2) ===");
        GeometryCalculator calcDefault = new GeometryCalculator();

        // Área do círculo
        calcDefault.calculateArea(5); // raio = 5

        // Área do retângulo
        calcDefault.calculateArea(4, 6); // base = 4, altura = 6

        // Área do triângulo (Heron)
        calcDefault.calculateArea(3, 4, 5); // lados = 3,4,5

        // Perímetro de um quadrado
        calcDefault.calculatePerimeter(2, 2, 2, 2);

        System.out.println("\n=== Teste com construtor de precisão 4 ===");
        GeometryCalculator calcPrecise = new GeometryCalculator(4);

        // Área do círculo com maior precisão
        calcPrecise.calculateArea(7.123);

        // Área do retângulo com números decimais
        calcPrecise.calculateArea(3.5, 6.25);

        // Área do triângulo (Heron) com números decimais
        calcPrecise.calculateArea(3.1, 4.2, 5.3);

        // Perímetro de um pentágono com lados decimais
        calcPrecise.calculatePerimeter(1.5, 2.0, 2.5, 3.0, 3.5);
    }
}