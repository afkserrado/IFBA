package ifba.inf011.singleton;

// Lazy initialization | Thread-safe | Sem overhead | Sem desperdício de memória
public class BillPughSingleton {

    // Construtor privado: impede instanciação externa com o operador new
    private BillPughSingleton() {}

    // Classe interna estática responsável por armazenar a instância
    // A JVM só carrega esta classe quando ela é referenciada pela
    // primeira vez, ou seja, na primeira chamada a getInstance()
    // Isso garante lazy initialization sem necessidade de sincronização
    private static class Holder {

        // Instância criada no carregamento de Holder
        // A JLS (Java Language Specification) garante que a inicialização de classes é thread-safe, eliminando a necessidade de locks
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {

        // A primeira chamada aqui dispara o carregamento de Holder, que por sua vez inicializa INSTANCE de forma lazy e thread-safe
        // Chamadas subsequentes simplesmente retornam a instância já criada
        return Holder.INSTANCE;
    }
}