package ifba.inf011.s24_1.interfaces.builders;

import ifba.inf011.s24_1.interfaces.products.Arma;

// Interface builder
// Contém os métodos para construir um produto (Espada etc.)
public interface BuilderArma {
    public Arma build();
    public void reset();
    public void setAdicionalRapido(double adicional);
    public void setHabilitada(Boolean hab);
    // demais métodos construtivos...
}
