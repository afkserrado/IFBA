package br.ifba.l1f;

public class App {

    public static void main(String[] args) {
        Contador produto = new Contador();
        int itens;

        produto.incrementar();
        produto.incrementar();
        produto.incrementar();
        produto.incrementar();
        produto.incrementar();
        itens = produto.getItens();
        System.out.println("Itens: " + itens);

        produto.zerar();
        itens = produto.getItens();
        System.out.println("Itens: " + itens);
    }
}