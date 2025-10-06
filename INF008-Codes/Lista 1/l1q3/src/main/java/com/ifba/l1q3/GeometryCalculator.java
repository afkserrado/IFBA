package com.ifba.l1q3;

public class GeometryCalculator {
    private double area;
    private int precision = 2; // Precisão padrão de casas decimais

    // Construtor com precisão de casas decimais
    public GeometryCalculator(int precision) {
        this.precision = precision;
    }

    // Construtor que chama o outro usando this()
    public GeometryCalculator() {
        this(2);
    }

    // Imprime
    private void print(String shape, String parameter, double value) {
        String unity = "m2";
        if (parameter.equals("Perímetro")) unity = "m";

        System.out.println(parameter + " do " + shape + " = " + String.format("%." + precision + "f", value) + " " + unity);
    }

    // Área do círculo
    public void calculateArea(double radius) {
        this.area = Math.PI * Math.pow(radius, 2);
        print("círculo", "Área", this.area);
    }

    // Área do retângulo
    public void calculateArea(double base, double height) {
        this.area = base * height;
        print("retângulo", "Área", this.area);
    }

    // Área do triângulo pela fórmula de Heron
    public void calculateArea(double side1, double side2, double side3) {
        double p; // Semiperímetro
        p = (side1 + side2 + side3) / 2;
        this.area = Math.sqrt((p* (p - side1) * (p - side2) * (p - side3)));
        print("triângulo", "Área", this.area);
    }

    // Calcula o perímetro
    public void calculatePerimeter(double ... sides) { // Cria um array com os parâmetros
        double perimeter = 0;

        for (double side : sides) { // Enchanced loop
            perimeter += side;
        }
        print("polígono", "Perímetro", perimeter);
    }
}
