package ifba.inf011.p2.s22_1.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ifba.inf011.p2.s22_1.model.GrupoMuscular;
import ifba.inf011.p2.s22_1.model.TipoExercicio;

public abstract class AbstractExercicioComponent implements ExercicioComponent {
    
    protected String nome;
    protected Set<TipoExercicio> categoria;
    protected Set<GrupoMuscular> gruposMusculares;
    private ExercicioComponent pai;
    
    public AbstractExercicioComponent(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<TipoExercicio> getCategoria() {
        return new ArrayList<>(categoria);
    }

    @Override
    public List<GrupoMuscular> getGruposMusculares() {
        return new ArrayList<>(gruposMusculares);
    }

    public ExercicioComponent getPai() {
        return this.pai;
    }

    public void setPai(ExercicioComponent pai) {
        this.pai = pai;
    } 
}
