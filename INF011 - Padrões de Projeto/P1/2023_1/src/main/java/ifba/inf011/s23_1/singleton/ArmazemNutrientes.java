package ifba.inf011.s23_1.singleton;

import ifba.inf011.s23_1.classes.Nutriente;

public interface ArmazemNutrientes {
    public Nutriente create(String nome, String unidade, Double caloriaPorUnidade);
    public Nutriente create(String nome) throws Exception;
}
