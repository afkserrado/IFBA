/*
2. Escreva em Java uma classe Ponto2D que represente um ponto no plano cartesiano. Além dos atributos por você identificados, a classe deve oferecer os seguintes membros:
a) Construtores sobrecarregados que permitam a inicialização do ponto:
i) Por default (sem parâmetros) na origem do espaço 2D;
ii) Num local indicado por dois parâmetros do tipo double (indicando o valor de abcissa e ordenada do ponto que está sendo criado);
iii) Em um local indicado por outro ponto.
b) Métodos de acesso (getter/setter) dos atributos do ponto;
c) Métodos sobrecarregados de movimentação do ponto com os mesmos parâmetros indicados para os construtores;
d) Método de comparação semântica do ponto (equals);
e) Método de representação do objeto como String;
f) Método que permita calcular a distância do ponto que recebe a mensagem, para outro ponto;
g) Método que permita a criação de um novo ponto no mesmo local do ponto que recebeu a mensagem (clone).
 */

package br.ifba.l1f;

public class Ponto2d {
    private double x;
    private double y;

    // Construtor 1: origem do plano cartesiano
    public Ponto2d() {
        setX(0);
        setY(0);
    }

    // Construtor 2: indicar x,y
    public Ponto2d(double x, double y) {
        setX(x);
        setY(y);
    }

    // Construtor 3: indicar ponto
    public Ponto2d(Ponto2d ponto) {
        setX(ponto.x);
        setY(ponto.y);
    }

    // Métodos
    // Método mover 1: origem
    public void mover() {
        setX(0);
        setY(0);
    }

    // Método mover 2: indicar x,y
    public void mover(double x, double y) {
        setX(x);
        setY(y);
    }

    // Método mover 3: indicar ponto
    public void mover(Ponto2d ponto) {
        setX(ponto.x);
        setY(ponto.y);
    }

    // Método de comparação
    public boolean comparar(Ponto2d ponto) {
        return x == ponto.x && y == ponto.y;
    }

    // Método de conversão para string
    public String pointToString() {
        return x + ";" + y;
    }

    // Método para calcular distância
    public double dist(Ponto2d ponto) {
        return Math.sqrt(Math.pow(ponto.x - x, 2) + Math.pow(ponto.y - y, 2));
    }

    // Método para clonar ponto
    public Ponto2d clonar() {
        return new Ponto2d(this);
    }

    // Getters e setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private void setX(double x) {
        this.x = x;
    }

    private void setY(double y) {
        this.y = y;
    }
}