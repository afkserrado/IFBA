package ifba.inf011.prototype.registry;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.prototype.interfaces.Prototype;

// Armazena os protótipos pré-construídos prontos para serem copiados
public class PrototypeRegistry {
    
    private Map<String, Prototype> cache = new HashMap<>();

    public void put(String key, Prototype prototype) {
        cache.put(key, prototype);
    }

    // Retorna sempre um clone
    public Prototype get(String key) {
        Prototype prototype = cache.get(key);

        if(prototype == null) {
            throw new IllegalArgumentException("Prototype não encontrado: " + key);
        }

        return prototype.clone();
    }
}
