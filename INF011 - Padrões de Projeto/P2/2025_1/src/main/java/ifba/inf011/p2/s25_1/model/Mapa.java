package ifba.inf011.p2.s25_1.model;

import java.util.List;

import ifba.inf011.p2.s25_1.enums.Dificuldade;
import ifba.inf011.p2.s25_1.enums.Idade;
import ifba.inf011.p2.s25_1.enums.Sexo;

public record Mapa(
    Sexo sexo,
    Idade idade,
    Dificuldade dificuldade,
    int escala,
    int ecn,
    PontoGeografico largada,
    PontoGeografico partida,
    List<Prisma> prismas,
    PontoGeografico chegada
) {

	public Mapa() {
		this(
				null,		// Sexo
				null, 		// Idade
				null, 		// Dificuldade
				0, 			// Escala
				0, 			// ECN
				null, 		// Largada
				null, 		// Partida
				List.of(), 	// Prismas
				null);		// Chegada
	}
}