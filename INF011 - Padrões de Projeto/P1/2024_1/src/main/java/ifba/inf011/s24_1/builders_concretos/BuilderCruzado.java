package ifba.inf011.s24_1.builders_concretos;

import ifba.inf011.s24_1.interfaces.builders.BuilderPersonagem;
import ifba.inf011.s24_1.interfaces.products.Arma;
import ifba.inf011.s24_1.products.Cruzado;
import ifba.inf011.s24_1.products.Espada;

// Builder concreto
// Contém os métodos para construir um produto (Cruzado)
public class BuilderCruzado implements BuilderPersonagem {
    
    private Cruzado personagem;

    public BuilderCruzado() {
        this.personagem = new Cruzado();
    }

    public Cruzado build() {
        System.out.println("Personagem criado: " + this.personagem);
        return this.personagem;
    }

    @Override
    public Cruzado buildStarter(Arma arma) {
        if (!(arma instanceof Espada)) {
            throw new IllegalArgumentException("Cruzado starter deve iniciar com Espada");
        }

        this.personagem.addArma(arma); // Exceção ao default
        return this.build();
    }

    @Override
    public void reset() {
        this.personagem = new Cruzado();
    }

    // Métodos para configurar o produto (Cruzado)
    @Override
    public void setAtaqueRapido(Double ataque) {
        this.personagem.setAtaqueRapido(ataque);
    }

    @Override
    public void setHabilidade(Boolean hab) {
        this.personagem.setHabilidade(hab);
    }

    @Override
    public void addArma(Arma arma) {
        this.personagem.addArma(arma);
    }
    // demais métodos construtivos...
}
