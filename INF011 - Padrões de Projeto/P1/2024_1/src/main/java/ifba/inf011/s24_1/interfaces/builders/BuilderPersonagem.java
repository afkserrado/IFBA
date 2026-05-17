package ifba.inf011.s24_1.interfaces.builders;

import ifba.inf011.s24_1.interfaces.products.Arma;
import ifba.inf011.s24_1.interfaces.products.Personagem;

// Interface builder
// Contém os métodos para construir um produto (Cruzado etc.)
public interface BuilderPersonagem {
    public Personagem build();
    public Personagem buildStarter(Arma arma);
    public void reset();
    public void setAtaqueRapido(Double ataque);
    public void setHabilidade(Boolean hab);
    public void addArma(Arma arma);
    // demais métodos construtivos...
}
