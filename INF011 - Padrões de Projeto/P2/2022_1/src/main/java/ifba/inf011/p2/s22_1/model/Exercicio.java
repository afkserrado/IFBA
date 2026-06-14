package ifba.inf011.p2.s22_1.model;

import java.util.LinkedHashSet;
import java.util.List;

import ifba.inf011.p2.s22_1.composite.AbstractExercicioComponent;
import ifba.inf011.p2.s22_1.composite.Cacheavel;

// Leaf do Composite
public class Exercicio extends AbstractExercicioComponent {

    private Equipamento equipamento;

    public Exercicio(String nome, List<TipoExercicio> categoria, List<GrupoMuscular> gruposMusculares) {
        this(nome, categoria, gruposMusculares, null);
    }

    public Exercicio(String nome, List<TipoExercicio> categoria, List<GrupoMuscular> gruposMusculares, Equipamento equipamento) {
        super(nome);
        this.categoria = new LinkedHashSet<>(categoria);
        this.gruposMusculares = new LinkedHashSet<>(gruposMusculares);
        this.equipamento = equipamento;
    }

    @Override
    public void setCategoria(List<TipoExercicio> categoria) {
        this.categoria = new LinkedHashSet<>(categoria);

        if(getPai() instanceof Cacheavel cacheavel) {
            cacheavel.invalidarCache();
        }
    }

    @Override
    public void setGruposMusculares(List<GrupoMuscular> gruposMusculares) {
        this.gruposMusculares = new LinkedHashSet<>(gruposMusculares);

        if(getPai() instanceof Cacheavel cacheavel) {
            cacheavel.invalidarCache();
        }
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    @Override
    public String toString() {
        return "Exercicio[nome=" + nome + ", categoria=" + categoria +
               ", gruposMusculares=" + gruposMusculares + ", equipamento=" + equipamento + "]";
    }
}