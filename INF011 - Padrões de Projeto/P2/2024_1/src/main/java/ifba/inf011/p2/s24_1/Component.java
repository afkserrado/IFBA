package ifba.inf011.p2.s24_1;

import ifba.inf011.p2.s24_1.model.Credencial;

// Component do Composite
// ServiceInterface/Subject do Proxy
public interface Component {
    public Long getTamanho();

    // Métodos default implementados para garantir a transparência

    default void ler(Credencial credencial) {
        // Exceção para pastas (composite)
        throw new UnsupportedOperationException("Este componente não possui controle de acesso.");
    }

    default void adicionar(Component component) {
        // Exceção para proxys e arquivos (folhas)
        throw new UnsupportedOperationException("Este componente não pode ter filhos.");
    }

    default void remover(Component component) {
        // Exceção para proxys e arquivos (folhas)
        throw new UnsupportedOperationException("Este componente não pode ter filhos.");
    }
}
