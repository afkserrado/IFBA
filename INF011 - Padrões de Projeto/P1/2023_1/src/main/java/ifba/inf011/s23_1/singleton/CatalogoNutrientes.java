package ifba.inf011.s23_1.singleton;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.s23_1.classes.Nutriente;

// Singleton
public class CatalogoNutrientes implements ArmazemNutrientes {
    
    private static CatalogoNutrientes instance; // Instância única
    private final Map<String, Nutriente> catalogo;

    private CatalogoNutrientes() { // Construtor privado
        catalogo = new HashMap<>();
    }

    // Cria ou obtém a instância do catálogo
    public static CatalogoNutrientes getInstance() {
        if(instance == null) {
            instance = new CatalogoNutrientes();
        }

        return instance;
    }

    @Override
    public Nutriente create(String nome, String unidade, Double caloriaPorUnidade) {
        Nutriente nutriente = catalogo.get(nome);
        
        if(nutriente == null) {
            nutriente = new Nutriente(nome);
            catalogo.put(nome, nutriente);
        }

        // Atualiza o nutriente
        nutriente.setUnidade(unidade);
        nutriente.setCaloriaPorUnidade(caloriaPorUnidade);

        return nutriente;           
    }

    @Override
    public Nutriente create(String nome) throws Exception {
        Nutriente nutriente = catalogo.get(nome);

        if(nutriente == null) {
            throw new Exception("Nutriente não existe e não pode ser criado apenas com o nome.");
        }

        return nutriente;
    }
}
