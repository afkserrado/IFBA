package ifba.inf011.singleton;

// Sem lazy initialization | Thread-safe | Sem overhead | Pode desperdiçar memória
public class StaticBlockSingleton {

    // Referência estática à única instância da classe
    // Inicializada no bloco estático abaixo, no carregamento da classe
    private static StaticBlockSingleton instance;

    // Construtor privado: impede instanciação externa com o operador new
    private StaticBlockSingleton() {}

    // Bloco estático executado uma única vez, no carregamento da classe pela JVM
    // Vantagem sobre eager initialization: permite capturar exceções verificadas lançadas pelo construtor, tratando-as durante a inicialização da classe
    // Não é lazy-loaded: a instância é criada mesmo que nunca seja usada, o que pode ser um problema em inicializações custosas
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            // Encapsula qualquer exceção verificada em RuntimeException, pois blocos estáticos não permitem propagar checked exceptions
            throw new RuntimeException("Exception occurred in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {

        // Retorna diretamente a instância criada no bloco estático
        // Nenhuma verificação de null é necessária: instance nunca será null
        return instance;
    }
}