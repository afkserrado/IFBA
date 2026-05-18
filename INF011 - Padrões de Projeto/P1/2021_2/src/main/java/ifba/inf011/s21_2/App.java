package ifba.inf011.s21_2;

import ifba.inf011.s21_2.concrete_builders.CursoBuilder;
import ifba.inf011.s21_2.concrete_builders.EmentaBuilder;
import ifba.inf011.s21_2.concrete_creators.ProductCreator;
import ifba.inf011.s21_2.concrete_products.Curso;
import ifba.inf011.s21_2.concrete_products.Disciplina;
import ifba.inf011.s21_2.concrete_products.Ementa;
import ifba.inf011.s21_2.concrete_products.Livro;
import ifba.inf011.s21_2.directors.CursoDirector;
import ifba.inf011.s21_2.enums.ProductType;
import ifba.inf011.s21_2.interfaces.Creator;
import ifba.inf011.s21_2.interfaces.Product;
import ifba.inf011.s21_2.singleton.CatalogoCursos;

public class App {

    public void q1() {
        Creator creator = new ProductCreator();

        Product livro = creator.factoryMethod(
            ProductType.LIVRO,
            "L001",
            "Padrões de Projeto"
        );

        Product disciplina = creator.factoryMethod(
            ProductType.DISCIPLINA,
            "D001",
            "Factory Method"
        );

        System.out.println("Questão 1");
        System.out.println(livro);
        System.out.println(disciplina);
        System.out.println();
    }

    public void q2() {
        CursoBuilder b1 = new CursoBuilder();
        CursoDirector<Curso> d = new CursoDirector<>(b1);

        Disciplina[] disciplinas = {
            new Disciplina("D001", "Factory Method"),
            new Disciplina("D002", "Abstract Factory")
        };

        Livro[] livros = {
            new Livro("L001", "Padrões de Projeto")
        };

        Curso curso = d.construirCurso(
            "C001",
            "Padrões Criacionais",
            disciplinas,
            livros
        );

        EmentaBuilder ementaBuilder = new EmentaBuilder();
        CursoDirector<Ementa> directorEmenta = new CursoDirector<>(ementaBuilder);

        Ementa ementa = directorEmenta.construirCurso(
            "C001",
            "Padrões Criacionais",
            disciplinas,
            livros
        );

        System.out.println("Questão 2");
        System.out.println(curso);
        System.out.println(ementa);
        System.out.println();
    }

    public void q3() {
        CursoBuilder b1 = new CursoBuilder();
        CursoDirector<Curso> d = new CursoDirector<>(b1);

        Disciplina[] disciplinas = {
            new Disciplina("D001", "Factory Method"),
            new Disciplina("D002", "Abstract Factory")
        };

        disciplinas[0].setCargaHoraria(20);
        disciplinas[1].setCargaHoraria(30);

        Livro[] livros = {
            new Livro("L001", "Padrões de Projeto")
        };

        Curso cursoModelo = d.construirCurso(
            "C001",
            "Padrões Criacionais",
            disciplinas,
            livros
        );

        CatalogoCursos catalogo = CatalogoCursos.getInstance();
        catalogo.registrarCurso(cursoModelo);

        Curso cursoClonado = catalogo.recuperarCurso("Padrões Criacionais");
        cursoClonado.getDisciplinas().get(0).setCargaHoraria(999);

        System.out.println("Questão 3");
        System.out.println("Curso modelo: " + cursoModelo);
        System.out.println("Curso clonado: " + cursoClonado);
        System.out.println();
    }

    public static void main(String[] args) {
        App app = new App();

        app.q1();
        app.q2();
        app.q3();

        /*
         * Saída esperada para q3:
         * Curso modelo: Curso{codigo='C001', nome='Padrões Criacionais', disciplinas=[Disciplina{codigo='D001', nome='Factory Method', cargaHoraria=20}, Disciplina{codigo='D002', nome='Abstract Factory', cargaHoraria=30}], livros=[Livro{codigo=L001, nome=Padrões de Projeto}]}
         * Curso clonado: Curso{codigo='C001', nome='Padrões Criacionais', disciplinas=[Disciplina{codigo='D001', nome='Factory Method', cargaHoraria=999}, Disciplina{codigo='D002', nome='Abstract Factory', cargaHoraria=30}], livros=[Livro{codigo=L001, nome=Padrões de Projeto}]}
         */
    }
}