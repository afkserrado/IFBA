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

        System.out.println(livro);
        System.out.println(disciplina);
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
        Ementa ementa = directorEmenta.construirCurso("C001", "Padrões Criacionais", disciplinas, livros);

        System.out.println(curso);
        System.out.println(ementa);
    }

    public static void main(String[] args) {
        App app = new App();

        app.q1();
        app.q2();
    }
}