package edu.universidade.core;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        // Criando objetos usando os diferentes construtores
        Aluno aluno1 = new Aluno("2025A001", "Ana Silva");
        Aluno aluno2 = new Aluno("2025A002", "Bruno Costa", 20);
        Aluno aluno3 = new Aluno("2025A003", "Carla Souza", 22, "Engenharia de Software");

        // Exibindo dados dos alunos (apenas getters públicos disponíveis)
        System.out.println("Aluno 1: " + aluno1.getMatricula() + " - " + aluno1.getNome());
        System.out.println("Aluno 2: " + aluno2.getMatricula() + " - " + aluno2.getNome());
        System.out.println("Aluno 3: " + aluno3.getMatricula() + " - " + aluno3.getNome());

        // Tentativa de acessar getters privados (geraria erro de compilação)
        // System.out.println(aluno1.getIdade()); // Erro: método privado
        // System.out.println(aluno3.getCurso()); // Erro: método privado

        // System.out.println("\n(Os métodos getIdade() e getCurso() são privados, respeitando o princípio do menor privilégio.)");
    }
}
