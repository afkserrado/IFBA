package ifba.inf011.s23_1.classes;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.s23_1.singleton.CatalogoNutrientes;

public class Alimento {
	
    private String nome;
    private Map<Nutriente, Double> nutrientes;

    public Alimento(String nome) {
        this.nome = nome;
        this.nutrientes = new HashMap<>();
    }

    public Alimento(String nome, Double qtdeCHO, Double qtdePTN, Double qtdLIP) throws Exception {
        this.nome = nome;
        this.nutrientes.put(CatalogoNutrientes.getInstance().create("CARBOIDRATO"), qtdeCHO);
        this.nutrientes.put(CatalogoNutrientes.getInstance().create("PROTEINA"), qtdeCHO);
        this.nutrientes.put(CatalogoNutrientes.getInstance().create("GORDURA"), qtdeCHO);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addNutriente(Nutriente nutriente, Double qtde) {
        this.nutrientes.put(nutriente, qtde);
    }
}
