package ifba.inf011.p2.s21_2.model;

import java.util.HashSet;
import java.util.Set;

import ifba.inf011.p2.s21_2.composite.Component;
import ifba.inf011.p2.s21_2.decorator.LivroComponent;

// Composite do Component
// Modelo original ajustado para torná-lo compatível com o padrão Composite
public class Curso extends Produto implements Component {

    private Set<Component> filhos;
    private Set<LivroComponent> livros; // Cada curso armazena apenas seus próprios livros

    public Curso(String codigo, String nome) {
        super(codigo, nome);
        this.filhos = new HashSet<>();
        this.livros = new HashSet<>();
    }

    @Override
    public void addLivro(LivroComponent livro) {
        this.livros.add(livro);
    }

    // Retorna os livros do curso atual e seus subcursos
    @Override
    public Set<LivroComponent> getLivros() {
        
        HashSet<LivroComponent> resultado = new HashSet<>(livros);

        for(Component filho : filhos) {
            resultado.addAll(filho.getLivros());
        }

        return resultado;
    }

    // Retorna as disciplinas do curso atual e seus subcursos
    @Override
    public Set<Component> getDisciplinas() {
        
        HashSet<Component> resultado = new HashSet<>();

        for(Component filho : filhos) {
            resultado.addAll(filho.getDisciplinas());
        }

        return resultado;
    }

    @Override
    public int getCHCumprida() {

        int total = 0;

        for(Component filho : filhos) {
            total += filho.getCHCumprida();
        }

        return total;
    }

    @Override
    public int getCHTotal() {

        int total = 0;

        for(Component filho : filhos) {
            total += filho.getCHTotal();
        }

        return total;
    }

    @Override
    public double getPctCHCumprida() {

        int chTotal = getCHTotal();

        if(chTotal == 0)
            return 0.0;

        return ((double) getCHCumprida()) / chTotal;
    }

    @Override
    public double getPreco() {

        double precoComponentes = 0;

        for(Component filho : filhos) {
            precoComponentes += filho.getPreco();
        }

        double precoLivros = 0;

        for(LivroComponent livro : livros) {
            precoLivros += livro.getPreco();
        }

        return precoComponentes * 0.80 + precoLivros * 0.90;
    }

    @Override
    public void adicionar(Component filho) {
        this.filhos.add(filho);
    }

    @Override
    public void remover(Component filho) {
        this.filhos.remove(filho);
    }
}