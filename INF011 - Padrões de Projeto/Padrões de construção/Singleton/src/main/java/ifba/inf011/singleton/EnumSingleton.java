package ifba.inf011.singleton;

// Sem lazy initialization | Thread-safe | Sem overhead | Sem desperdício de memória
// Abordagem mais simples e segura em Java
// A JVM oferece quatro garantias exclusivas desta abordagem:
//   1. Thread-safe: constantes enum são inicializadas uma única vez no carregamento da classe
//   2. Serialização segura: serializar e desserializar retorna a mesma instância
//   3. Segurança contra Reflection: a JVM impede a criação de instâncias via reflection
//   4. Instância única garantida no nível da JVM, não pelo código da aplicação
// Limitação: enums não podem estender outras classes (já estendem java.lang.Enum), portanto não é adequado quando o Singleton precisa de uma classe base
public enum EnumSingleton {

    // A única instância do singleton, declarada como constante do enum
    INSTANCE;

    // Métodos de negócio são definidos normalmente aqui.
    public void doSomething() {
        // Lógica do singleton
    }
}