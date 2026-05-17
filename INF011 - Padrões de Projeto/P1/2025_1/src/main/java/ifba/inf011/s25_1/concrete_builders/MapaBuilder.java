package ifba.inf011.s25_1.concrete_builders;

import ifba.inf011.s25_1.classes.PontoGeografico;
import ifba.inf011.s25_1.classes.Prisma;
import ifba.inf011.s25_1.enums.Dificuldade;
import ifba.inf011.s25_1.enums.Idade;
import ifba.inf011.s25_1.enums.Sexo;
import ifba.inf011.s25_1.interfaces.BuilderPercurso;
import ifba.inf011.s25_1.products.Mapa;

public class MapaBuilder implements BuilderPercurso {

    private Mapa mapa;

    public MapaBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.mapa = new Mapa();
    }

    @Override
    public void setCategoria(Sexo sexo, Idade idade, Dificuldade dificuldade) {
        mapa.setSexo(sexo);
        mapa.setIdade(idade);
        mapa.setDificuldade(dificuldade);
    }

    @Override
    public void setECN(double ecn) {
        mapa.setEcn(ecn);
    }

    @Override
    public void setEscala(double escala) {
        mapa.setEscala(escala);
    }

    @Override
    public void setLargada(double lat, double lon, double alt) {
        mapa.setLargada(new PontoGeografico(lat, lon, alt));
    }

    @Override
    public void setPartida(double lat, double lon, double alt) {
        mapa.setPartida(new PontoGeografico(lat, lon, alt));
    }

    @Override
    public void addPrisma(int numero, double lat, double lon, double alt) {
        mapa.addPrisma(new Prisma(numero, new PontoGeografico(lat, lon, alt)));
    }

    @Override
    public void setChegada(double lat, double lon, double alt) {
        mapa.setChegada(new PontoGeografico(lat, lon, alt));
    }

    public Mapa getResultado() {
        return this.mapa;
    }
}