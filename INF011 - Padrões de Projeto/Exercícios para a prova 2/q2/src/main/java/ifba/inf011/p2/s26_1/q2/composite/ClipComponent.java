package ifba.inf011.p2.s26_1.q2.composite;

import java.util.List;

// Component do Composite
// Component do Decorator
public interface ClipComponent {

    public String render();
    public int getDuracao();

    // Lança exceção para leafs
    public default void adicionar(ClipComponent clip) {
        throw new UnsupportedOperationException("Clip simples não suporta adicionar componentes.");
    }

    // Lança exceção para leafs
    public default void remover(ClipComponent clip) {
        throw new UnsupportedOperationException("Clip simples não suporta remover componentes.");
    }

    // Lança exceção para leafs
    public default List<ClipComponent> getFilhos() {
        throw new UnsupportedOperationException("Clip simples não possui filhos.");
    }
}