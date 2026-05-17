package ifba.inf011.s25_1.interfaces;

import ifba.inf011.s25_1.enums.Dificuldade;
import ifba.inf011.s25_1.enums.Idade;
import ifba.inf011.s25_1.enums.Sexo;

// Builder
public interface BuilderPercurso {
    void reset();
    void setCategoria(Sexo sexo, Idade idade, Dificuldade dificuldade);
    void setECN(double ecn);
    void setEscala(double escala);
    void setLargada(double lat, double lon, double alt);
    void setPartida(double lat, double lon, double alt);
    void addPrisma(int numero, double lat, double lon, double alt);
    void setChegada(double lat, double lon, double alt);
}
