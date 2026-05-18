package ifba.inf011.s23_1.builder;

import ifba.inf011.s23_1.classes.Alimento;

public interface PreparoBuilder {
    public void reset();
    public void setNome(String nome);
    public void setLowCarb();
    public void setVegano();
    public void addAlimento(Alimento alimento);
    // outros métodos...
}
