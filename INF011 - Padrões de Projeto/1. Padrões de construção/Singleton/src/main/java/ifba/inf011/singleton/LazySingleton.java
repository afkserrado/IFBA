package ifba.inf011.singleton;

// Lazy initialization | Não thread-safe | Sem overhead | Sem desperdício de memória
public class LazySingleton {
    
    // Referência estática à única instância da classe
    // Permanece null até a primeira chamada a getInstance()
    private static LazySingleton instance;

    // Construtor privado: impede instanciação externa com o operador new
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        
        // Se a instância ainda não foi criada, cria agora (lazy initialization)
        // Não é thread-safe: duas threads podem passar por aqui simultaneamente
        if(instance == null) {
            instance = new LazySingleton();
        }

        // Retorna a instância existente (recém-criada ou já armazenada)
        return instance;
    }
}
