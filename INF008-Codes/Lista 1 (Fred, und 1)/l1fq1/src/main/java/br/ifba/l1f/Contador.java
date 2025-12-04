/* 
1) Escreva em Java uma classe Contador, que encapsule um valor usado para contagem de itens ou eventos. A classe deve oferecer métodos que devem:
a) Zerar;
b) Incrementar;
c) Retornar o valor do contador.
*/

package br.ifba.l1f;

public class Contador {
    private int itens;

    // Construtor default

    public void zerar() {
        itens = 0;
    }

    public void incrementar() {
        itens++;
    }

    public int getItens() {
        return itens;
    }
}