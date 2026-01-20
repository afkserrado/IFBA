package edu.universidade.core;

/*
Pergunta: Implemente os construtores encadeados e determine quais getters devem ser públicos. Justifique a escolha de visibilidade baseado no princípio de menor privilégio.
*/

public class Aluno {
    private String matricula;
    private String nome;
    private int idade;
    private String curso;

    // TODO: Criar 3 construtores sobrecarregados:
    // 1. Apenas matricula e nome
    // 2. Matricula, nome e idade
    // 3. Matricula, nome, idade e curso
    // Um deve chamar o outro para evitar duplicação

    // Construtores
    public Aluno(String matricula, String nome) {
        validarMatricula(matricula);
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno(String matricula, String nome, int idade) {
        this(matricula, nome);
        this.idade = idade;
    }

    public Aluno(String matricula, String nome, int idade, String curso) {
        this(matricula, nome, idade);
        this.curso = curso;
    }

    // Métodos privados
    private void validarMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula não poder ser nula ou vazia.");
        }
    }

    // Getters
    public String getMatricula() { 
        return matricula; 
    }

    public String getNome() {
        return nome;
    }

    private int getIdade() {
        return idade;
    }

    private String getCurso() {
        return curso;
    }

    // TODO: Definir quais getters são realmente necessários
    // TODO: Criar método privado validarMatricula(String matricula)
}
