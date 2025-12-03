class Animal {

    // Construtor default
}

class Cachorro extends Animal {

    // Construtor
    Cachorro(String nome) {
        System.out.println("Um cachorro foi criado.");
    }
}

public class teste {
    public static void main(String[] args) {
        new Cachorro("Amora");
    }
}