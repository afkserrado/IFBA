package ifba.inf011.p2.s22_1.model;

import java.util.ArrayList;
import java.util.List;

public class Programa {

    private TipoPrograma tipo;
    private List<Serie> series;

    public Programa() {
        this.series = new ArrayList<Serie>();
    }

    public TipoPrograma getTipo() {
        return tipo;
    }

    public void setTipo(TipoPrograma tipo) {
        this.tipo = tipo;
    }

    public List<Serie> getSeries() {
        return new ArrayList<Serie>(series);
    }

    public void setSeries(List<Serie> series) {
        this.series = new ArrayList<Serie>(series);
    }

    public void adicionarSerie(Serie serie) {
        this.series.add(serie);
    }

    @Override
    public String toString() {
        return "Programa[tipo=" + tipo + ", series=" + series + "]";
    }
}