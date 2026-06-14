package ifba.inf011.p2.s21_2;

import ifba.inf011.p2.s21_2.adapter.WebinarAdapter;
import ifba.inf011.p2.s21_2.adapter.WebinarIF;
import ifba.inf011.p2.s21_2.composite.Component;
import ifba.inf011.p2.s21_2.decorator.LivroColoridoDecorator;
import ifba.inf011.p2.s21_2.decorator.LivroComponent;
import ifba.inf011.p2.s21_2.decorator.LivroDigitalDecorator;
import ifba.inf011.p2.s21_2.model.Curso;
import ifba.inf011.p2.s21_2.model.Disciplina;
import ifba.inf011.p2.s21_2.model.Livro;

public class Main {

    public static void main(String[] args) {

        // =========================
        // LIVROS (Decorator)
        // =========================
        Livro livroBase = new Livro("L1", "GoF", "ISBN-001", 100.0);

        LivroComponent livroDigital = new LivroDigitalDecorator(livroBase);
        LivroComponent livroColorido = new LivroColoridoDecorator(livroBase);
        LivroComponent livroDigitalColorido =
                new LivroColoridoDecorator(new LivroDigitalDecorator(livroBase));

        System.out.println("Livro base: " + livroBase.getPreco());
        System.out.println("Digital: " + livroDigital.getPreco());
        System.out.println("Colorido: " + livroColorido.getPreco());
        System.out.println("Digital + Colorido: " + livroDigitalColorido.getPreco());

        // =========================
        // DISCIPLINAS
        // =========================
        Disciplina d1 = new Disciplina("D1", "POO", 50, 60, 30);
        Disciplina d2 = new Disciplina("D2", "SO", 70, 80, 80);

        // =========================
        // WEBINAR (Adapter)
        // =========================
        WebinarIF webinarMock = new WebinarIF() {
            @Override public String getId() { return "W1"; }
            @Override public String getTitle() { return "Design Patterns Talk"; }
            @Override public double getPrice() { return 40.0; }
            @Override public void play() {}
            @Override public String getHoster() { return "GoF Speaker"; }
            @Override public long getMinutes() { return 90; }
            @Override public boolean wasWatched() { return true; }
        };

        Component webinar = new WebinarAdapter(webinarMock);

        // =========================
        // CURSOS (Composite)
        // =========================
        Curso cursoJava = new Curso("C1", "Java Básico");
        cursoJava.adicionar(d1);
        cursoJava.addLivro(livroBase);

        Curso cursoPatterns = new Curso("C2", "Padrões");
        cursoPatterns.adicionar(d2);
        cursoPatterns.adicionar(cursoJava);
        cursoPatterns.adicionar(webinar);

        cursoPatterns.addLivro(livroDigital);
        cursoPatterns.addLivro(livroColorido);

        // =========================
        // TESTES
        // =========================
        System.out.println("\n===== CURSO PADRÕES =====");

        System.out.println("Preço total: " + cursoPatterns.getPreco());
        System.out.println("CH total: " + cursoPatterns.getCHTotal());
        System.out.println("CH cumprida: " + cursoPatterns.getCHCumprida());
        System.out.println("Percentual: " + cursoPatterns.getPctCHCumprida());

        System.out.println("Disciplinas no curso: " +
                cursoPatterns.getDisciplinas().size());

        System.out.println("Livros no curso: " +
                cursoPatterns.getLivros().size());
    }

    /*
    Livro base: 100.0
    Digital: 85.0
    Colorido: 105.0
    Digital + Colorido: 89.25

    ===== CURSO PADRÕES =====
    Preço total: 363.0
    CH total: 142
    CH cumprida: 112
    Percentual: 0.7887323943661971
    Disciplinas no curso: 3
    Livros no curso: 3
    */
}