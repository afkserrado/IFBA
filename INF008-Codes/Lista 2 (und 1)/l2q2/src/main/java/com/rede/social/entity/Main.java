package com.rede.social.entity;

public class Main {
    public static void main(String[] args) {
        // Criando um post
        Post post1 = new Post("Olá mundo!", "Anderson");

        // Curtindo o post
        post1.curtir();
        post1.curtir(2);

        // Exibindo curtidas
        System.out.println("Curtidas do post: " + post1.getCurtidas());

        // Tentando deslike
        post1.deslike();
        System.out.println("Curtidas após 1 deslike: " + post1.getCurtidas());

        post1.deslike(2);
        System.out.println("Curtidas após 2 deslikes: " + post1.getCurtidas());

        // Testando exceção (deslike maior que curtidas atuais)
        try {
            post1.deslike(10);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Testando outro post
        Post post2 = new Post("Meu segundo post", "Maria", false);
        System.out.println("Post de " + post2.getAutor() + " criado com sucesso!");
    }
}
