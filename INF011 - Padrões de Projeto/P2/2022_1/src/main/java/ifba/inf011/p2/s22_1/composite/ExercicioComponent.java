package ifba.inf011.p2.s22_1.composite;

import java.util.List;

import ifba.inf011.p2.s22_1.model.GrupoMuscular;
import ifba.inf011.p2.s22_1.model.TipoExercicio;

// Component do Composite
public interface ExercicioComponent {

    public String getNome();
    public void setNome(String nome);
    public List<TipoExercicio> getCategoria();
    public List<GrupoMuscular> getGruposMusculares();

    // Lança exceção para composites
    default void setCategoria(List<TipoExercicio> categoria) {
        throw new UnsupportedOperationException("Esse objeto não pode ter filhos.");
    }

    // Lança exceção para composites
    default void setGruposMusculares(List<GrupoMuscular> gruposMusculares) {
        throw new UnsupportedOperationException("Esse objeto não pode ter filhos.");
    }

    // Lança exceção para folhas
    default void add(ExercicioComponent component) {
        throw new UnsupportedOperationException("Esse objeto não pode ter filhos.");
    }

    // Lança exceção para folhas
    default void remove(ExercicioComponent component) {
        throw new UnsupportedOperationException("Esse objeto não pode ter filhos.");
    }
}
