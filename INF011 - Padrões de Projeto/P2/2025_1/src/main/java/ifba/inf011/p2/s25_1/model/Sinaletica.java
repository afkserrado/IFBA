package ifba.inf011.p2.s25_1.model;

import ifba.inf011.p2.s25_1.enums.Dificuldade;
import ifba.inf011.p2.s25_1.enums.Idade;
import ifba.inf011.p2.s25_1.enums.Sexo;

public record Sinaletica(
    Sexo sexo,
    Idade idade,
    Dificuldade dificuldade,
    double distancia,
    double desnivel
) {}