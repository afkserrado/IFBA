package ifba.inf011.singleton;

// Sem lazy initialization | Thread-safe | Sem overhead | Pode desperdiçar memória
public class EagerSingleton {

    // Instância criada imediatamente no carregamento da classe, antes de qualquer thread acessá-la. A JVM garante que a inicialização de campos estáticos ocorre apenas uma vez, tornando esta abordagem inerentemente thread-safe sem necessidade de locks
    // Trade-off: a instância é criada mesmo que nunca seja utilizada, o que pode desperdiçar recursos em inicializações custosas
    private static final EagerSingleton instance = new EagerSingleton();

    // Construtor privado: impede instanciação externa com o operador new
    private EagerSingleton() {}

    public static EagerSingleton getInstance() {

        // Retorna diretamente a instância já criada no carregamento da classe
        // Nenhuma verificação de null é necessária: instance nunca será null
        return instance;
    }
}