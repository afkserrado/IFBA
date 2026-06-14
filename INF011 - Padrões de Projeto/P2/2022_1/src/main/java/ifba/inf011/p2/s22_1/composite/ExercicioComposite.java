package ifba.inf011.p2.s22_1.composite;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import ifba.inf011.p2.s22_1.model.GrupoMuscular;
import ifba.inf011.p2.s22_1.model.TipoExercicio;

// Composite do Composite
public class ExercicioComposite extends AbstractExercicioComponent implements Cacheavel {
    
    private final List<ExercicioComponent> filhos = new ArrayList<>();

    public ExercicioComposite(String nome) {
        super(nome);
        this.categoria = new LinkedHashSet<>();
        this.gruposMusculares = new LinkedHashSet<>();
    }

    @Override
    public List<TipoExercicio> getCategoria() {
        
        if (this.categoria == null || this.gruposMusculares == null) {
            recalcularAtributos();
        }

        return new ArrayList<>(categoria);
    }

    // Solução mais simples
    // @Override
    // public List<TipoExercicio> getCategoria() {
    //     Set<TipoExercicio> categorias = new HashSet<>();

    //     for (ExercicioComponent filho : filhos)
    //         categorias.addAll(filho.getCategoria());

    //     return new ArrayList<>(categorias);
    // }

    @Override
    public List<GrupoMuscular> getGruposMusculares() {
        
        if (this.categoria == null || this.gruposMusculares == null) {
            recalcularAtributos();
        }
        
        return new ArrayList<>(gruposMusculares);
    }

    @Override
    public void add(ExercicioComponent exercicio) {
        
        filhos.add(exercicio);

        if(exercicio instanceof AbstractExercicioComponent filho) {
            filho.setPai(this);
        }

        invalidarCache();
    }

    @Override
    public void remove(ExercicioComponent exercicio) {
        
        filhos.remove(exercicio);

        if(exercicio instanceof AbstractExercicioComponent filho) {
            filho.setPai(null);
        }

        invalidarCache();
    }

    private void recalcularAtributos() {
        
        this.categoria = new LinkedHashSet<>();
        this.gruposMusculares = new LinkedHashSet<>();

        for (ExercicioComponent filho : filhos) {
            this.categoria.addAll(filho.getCategoria());
            this.gruposMusculares.addAll(filho.getGruposMusculares());
        }
    }

    @Override
    public void invalidarCache() {
        this.categoria = null;
        this.gruposMusculares = null;

        if(getPai() instanceof Cacheavel cacheavel) {
            cacheavel.invalidarCache();
        }
    }
}
