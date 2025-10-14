package com.rede.social.entity;

/*
Pergunta: Complete a classe implementando: (1) Construtores que evitem duplicação usando this, (2) Métodos curtir sobrecarregados que compartilhem lógica de validação através de método privado, (3) Validações adequadas nos construtores.
*/

public class Post {
    private String conteudo;
    private String autor;
    private int curtidas;
    private boolean publico;

    // Construtores
    public Post(String conteudo, String autor) {
        this(conteudo, autor, true);
    }

    public Post(String conteudo, String autor, boolean publico) {
        validarConteudo(conteudo); // Se lançar exceção, nada abaixo será executado
        this.conteudo = conteudo;
        this.autor = autor;
        this.publico = publico;
    }

    // Métodos publicos
    public void curtir() {
        curtir(1);
    }

    public void curtir(int quantidade) {
        validarCurtidas(quantidade);
        curtidas += quantidade;
    }

    // Métodos publicos
    public void deslike() {
        deslike(1);
    }

    public void deslike(int quantidade) {
        validarDeslikes(quantidade);
        curtidas -= quantidade;
    }

    // Métodos privados para validação
    private void validarCurtidas(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de curtidas não pode ser negativa."); // Exceção
        }
    }

    private void validarDeslikes(int quantidade) {
        if (quantidade <= 0 || quantidade > curtidas) {
            throw new IllegalArgumentException("Quantidade de deslikes não pode ser negativa ou maior que a quantidade de curtidas atual."); // Exceção
        }
    }

    private void validarConteudo(String conteudo) {
        if (conteudo == null || conteudo.trim().isEmpty()) {
            throw new IllegalArgumentException("Conteúdo não pode ser vazio."); // Exceção
        }
    }

    // Getters para teste
    public int getCurtidas() {
        return curtidas;
    }

    public String getAutor() {
        return autor;
    }

}
