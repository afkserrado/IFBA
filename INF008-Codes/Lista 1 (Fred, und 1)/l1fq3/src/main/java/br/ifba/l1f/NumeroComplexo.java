/*
3) Escreva em Java uma classe NumeroComplexo, que representa um número complexo. A classe deve fornecer as seguintes operações:
a) Construtor com valores das partes inteira e fracionária;
b) Métodos getter/setter para os atributos da parte inteira e parte imaginária;
c) Método somar, que recebe outro número complexo e o adiciona ao número complexo que recebeu a mensagem. (a+bi)+(c+di) = (a+c)+(b+d)i;
d) Método subtrair, que recebe outro número complexo e o subtrai do número complexo que recebeu a mensagem. (a+bi)−(c+di) = (a−c)+(b−d)i;
e) Método multiplicar, que recebe outro número complexo e o multiplica ao complexo que recebeu a mensagem: (a+bi) * (c+di) = (ac−bd)+(ad+bc)i;
f) Método dividir, que recebe outro número complexo e o divide ao complexo que recebeu a mensagem: (a+bi) / (c+di) = (ac+bd)/(c2+d2) + (bc-ad)/(c2+d2)i;
g) Um método de comparação semântica dos números complexos;
h) Um método que gere e retorne a representação string do número complexo;
i) Um método que retorne o módulo do número complexo.
*/

package br.ifba.l1f;

public class NumeroComplexo {
    private double real;
    private double imag;

    // Construtor
    public NumeroComplexo(double real, double imag) {
        setReal(real);
        setImag(imag);
    }

    // Métodos
    public NumeroComplexo somar(NumeroComplexo n) {
        double rReal = this.real + n.real;
        double rImag = this.imag + n.imag;
        return new NumeroComplexo(rReal, rImag);
    }

    public NumeroComplexo subtrair(NumeroComplexo n) {
        double rReal = this.real - n.real;
        double rImag = this.imag - n.imag;
        return new NumeroComplexo(rReal, rImag);
    }

    public NumeroComplexo multiplicar(NumeroComplexo n) {
        double rReal = this.real * n.real - this.imag * n.imag;
        double rImag = this.real * n.imag + this.imag * n.real;
        return new NumeroComplexo(rReal, rImag);
    }

    public NumeroComplexo dividir(NumeroComplexo n) {
        double rReal = (this.real * n.real + this.imag * n.imag) / (Math.pow(n.real, 2) + Math.pow(n.imag, 2));
        double rImag = (this.imag * n.real - this.real * n.imag) / (Math.pow(n.real, 2) + Math.pow(n.imag, 2));
        return new NumeroComplexo(rReal, rImag);
    }

    public boolean comparar(NumeroComplexo n) {
        return real == n.real && imag == n.imag;
    }

    public String ncToString() {
        return real + (imag >= 0 ? "+" : "") + imag + "i";
    }

    public double modulo() {
        return Math.sqrt(Math.pow(real, 2) + Math.pow(imag, 2));
    }

    // Getters e setters
    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    private void setReal(double real) {
        this.real = real;
    }

    private void setImag(double imag) {
        this.imag = imag;
    }
}