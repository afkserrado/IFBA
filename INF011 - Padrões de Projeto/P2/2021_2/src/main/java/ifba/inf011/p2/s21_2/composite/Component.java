package ifba.inf011.p2.s21_2.composite;

import java.util.Collections;
import java.util.Set;

import ifba.inf011.p2.s21_2.decorator.LivroComponent;

// Interface Component
public interface Component {
    public int getCHCumprida();
    public int getCHTotal();
    public Set<Component> getDisciplinas();
    public double getPctCHCumprida();
    public double getPreco();
    
    // Implementação padrão para folhas
    default void adicionar(Component filho) {
        throw new UnsupportedOperationException("Este objeto não pode ter filhos.");
    }

    // Implementação padrão para folhas
    default void remover(Component filho) {
        throw new UnsupportedOperationException("Este objeto não pode ter filhos.");
    }

    // Implementação padrão para folhas
    default void addLivro(LivroComponent livro) {
        throw new UnsupportedOperationException("Este objeto não pode ter filhos.");
    }

    // Implementação padrão para folhas
    // Retorna um valor neutro
    default Set<LivroComponent> getLivros() {
        return Collections.emptySet();
    }
}
