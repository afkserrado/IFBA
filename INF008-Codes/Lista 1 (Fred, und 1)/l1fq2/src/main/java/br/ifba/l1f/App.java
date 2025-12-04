package br.ifba.l1f;

public class App {
    public static void main(String[] args) {
        
        // 1️⃣ Criar pontos com os diferentes construtores
        Ponto2d p1 = new Ponto2d(3, 4);
        Ponto2d p2 = new Ponto2d(p1);
        Ponto2d p3 = new Ponto2d();

        // 2️⃣ Testar getters e toString
        System.out.println("Ponto 1: " + p1.pointToString());
        System.out.println("Ponto 2: " + p2.pointToString());
        System.out.println("Ponto 3: " + p3.pointToString());

        // 3️⃣ Testar mover
        p3.mover(5, 6);
        System.out.println("Ponto 3 movido: " + p3.pointToString());

        p1.mover();
        System.out.println("Ponto 1 movido para origem: " + p1.pointToString());

        p2.mover(p3);
        System.out.println("Ponto 2 movido para Ponto 3: " + p2.pointToString());

        // 4️⃣ Testar comparação semântica
        boolean comp1 = p1.comparar(p3);
        boolean comp2 = p2.comparar(p3);
        System.out.println("Ponto 1 == Ponto 3? " + comp1);
        System.out.println("Ponto 2 == Ponto 3? " + comp2);

        // 5️⃣ Testar distância
        double distancia = p2.dist(p3);
        System.out.println("Distância entre Ponto 2 e Ponto 3: " + distancia);

        // 6️⃣ Testar clonagem
        Ponto2d p4 = p3.clonar();
        System.out.println("Ponto 4 (clone do Ponto 3): " + p4.pointToString());

        // Testar se clone é semântico igual ao original
        System.out.println("Ponto 4 == Ponto 3? " + p4.comparar(p3));
    }
}
