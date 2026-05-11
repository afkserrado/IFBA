package ifba.inf011.singleton;

// Lazy initialization | Thread-safe | Sem overhead | Sem desperdício de memória
class DoubleCheckedSingleton {

    // Referência estática à única instância da classe
    // volatile impede que a JVM reordene as instruções de: instance = new DoubleCheckedSingleton()
    // pois essa operação envolve três etapas internas:
    //   1. Alocar memória
    //   2. Chamar o construtor
    //   3. Atribuir a referência à variável
    // Sem volatile, outra thread poderia enxergar a referência não-nula
    // antes do objeto estar completamente inicializado
    private static volatile DoubleCheckedSingleton instance;

    // Construtor privado: impede instanciação externa com o operador new
    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {

        // Primeira verificação sem lock (fast path)
        // Evita o custo da sincronização quando a instância já existe
        if (instance == null) {

            // Adquire o lock somente quando a instância pode precisar ser criada
            // Apenas uma thread por vez executa o bloco abaixo
            synchronized (DoubleCheckedSingleton.class) {

                // Segunda verificação dentro do lock
                // Necessária porque duas threads podem ter passado pela primeira
                // verificação simultaneamente. A que adquiriu o lock primeiro
                // cria a instância; a segunda, ao entrar no bloco, encontra
                // instance != null e não cria uma segunda instância
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }

        // Retorna a instância existente (recém-criada ou já armazenada)
        return instance;
    }
}