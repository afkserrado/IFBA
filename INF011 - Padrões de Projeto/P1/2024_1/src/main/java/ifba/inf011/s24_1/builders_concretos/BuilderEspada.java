package ifba.inf011.s24_1.builders_concretos;

import ifba.inf011.s24_1.interfaces.builders.BuilderArma;
import ifba.inf011.s24_1.products.Espada;

// Builder concreto
// Contém os métodos para construir um produto (Espada)
public class BuilderEspada implements BuilderArma {
    
    private Espada arma;

    public BuilderEspada() {
        this.arma = new Espada();
    }

    public Espada build() {
        System.out.println("Arma criada: " + this.arma);
        return this.arma;
    }

    @Override
    public void reset() {
        this.arma = new Espada();
    }

    // Métodos para configurar o produto (Espada)
    @Override
    public void setAdicionalRapido(double adicional) {
        this.arma.setAdicionalRapido(adicional);
    }

    @Override
    public void setHabilitada(Boolean hab) {
        this.arma.setHabilitada(hab);
    }
    // demais métodos construtivos...
}
