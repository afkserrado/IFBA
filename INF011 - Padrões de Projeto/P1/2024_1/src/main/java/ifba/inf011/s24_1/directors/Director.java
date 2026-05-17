package ifba.inf011.s24_1.directors;

import ifba.inf011.s24_1.interfaces.builders.BuilderArma;
import ifba.inf011.s24_1.interfaces.builders.BuilderPersonagem;
import ifba.inf011.s24_1.interfaces.products.Arma;
import ifba.inf011.s24_1.interfaces.products.Personagem;

// Director
public class Director {
    
    private BuilderPersonagem builderPersonagem;
    private BuilderArma builderArma;

    public Director(BuilderPersonagem builderPersonagem, BuilderArma builderArma) {
        this.builderPersonagem = builderPersonagem;
        this.builderArma = builderArma;
    }

    public void mudarBuilders(BuilderPersonagem builderPersonagem, BuilderArma builderArma) {
        this.builderPersonagem = builderPersonagem;
        this.builderArma = builderArma;
    }

    public Personagem criarPersonagemInicial() {
        builderArma.reset();
        builderPersonagem.reset();
        
        builderArma.setHabilitada(true); // Exceção ao default
        
        Arma armaInicial = builderArma.build();

        return builderPersonagem.buildStarter(armaInicial);
    }
}
