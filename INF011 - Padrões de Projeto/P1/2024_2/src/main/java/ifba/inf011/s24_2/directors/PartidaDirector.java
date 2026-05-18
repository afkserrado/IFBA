package ifba.inf011.s24_2.directors;

import ifba.inf011.s24_2.interfaces.IPartidaBuilder;
import ifba.inf011.s24_2.products.Partida;

// Director
public class PartidaDirector {

    private IPartidaBuilder builder;

    public PartidaDirector(IPartidaBuilder builder) {
        this.builder = builder;
    }

    public Partida criarPartida(String descricao, String equipeA, String equipeB) {
        
        // Cria a instância do produto via builder
        builder.reset();
        
        Partida partida = (Partida) builder
            .setDescricao(descricao)
            .setEquipeA(equipeA)
            .setEquipeB(equipeB)
            .build();

        return partida;
    }
}
