package ifba.inf011.singleton;

// Lazy initialization | Thread-safe | Com overhead | Sem desperdício de memória
public class ThreadSafeSingleton {
    
    // Referência estática à única instância da classe
    // Permanece null até a primeira chamada a getInstance()
    private static ThreadSafeSingleton instance;

    // Construtor privado: impede instanciação externa com o operador new
    private ThreadSafeSingleton() {}

    // synchronized garante que apenas uma thread por vez execute este método
    // Quando uma thread entra, adquire o lock da classe
    // As demais threads ficam bloqueadas até o lock ser liberado
    public static synchronized ThreadSafeSingleton getInstance() {

        // Custo de desempenho: o lock é adquirido em TODA chamada a getInstance(), mesmo após a instância já ter sido criada, o que é desnecessário
        if(instance == null) {
            instance = new ThreadSafeSingleton();
        }

        // Retorna a instância existente (recém-criada ou já armazenada)
        return instance;
    }
}
