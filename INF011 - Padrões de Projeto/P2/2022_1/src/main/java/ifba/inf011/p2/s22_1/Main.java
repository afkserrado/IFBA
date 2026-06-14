package ifba.inf011.p2.s22_1;

import java.util.List;

import ifba.inf011.p2.s22_1.composite.ExercicioComposite;
import ifba.inf011.p2.s22_1.model.Exercicio;
import ifba.inf011.p2.s22_1.model.GrupoMuscular;
import ifba.inf011.p2.s22_1.model.Halteres;
import ifba.inf011.p2.s22_1.model.Maquinas;
import ifba.inf011.p2.s22_1.model.TipoExercicio;

public class Main {

    public static void main(String[] args) {

        // Exercícios (folhas)
        Exercicio supino = new Exercicio(
                "Supino Reto",
                List.of(TipoExercicio.FORCA),
                List.of(GrupoMuscular.PEITORAL, GrupoMuscular.TRICEPS),
                new Halteres("HAL-10", 2, 10.0));

        Exercicio puxada = new Exercicio(
                "Puxada Frontal",
                List.of(TipoExercicio.FORCA),
                List.of(GrupoMuscular.COSTAS, GrupoMuscular.BICEPS),
                new Maquinas("MAQ-01", 1, "Pulley", "Movement"));

        Exercicio corrida = new Exercicio(
                "Corrida",
                List.of(TipoExercicio.CARDIO),
                List.of(GrupoMuscular.MEMBROS_INFERIORES));

        // Composite: treino superior
        ExercicioComposite treinoSuperior = new ExercicioComposite("Treino Superior");
        treinoSuperior.add(supino);
        treinoSuperior.add(puxada);

        // Composite: treino completo
        ExercicioComposite treinoCompleto = new ExercicioComposite("Treino Completo");
        treinoCompleto.add(treinoSuperior);
        treinoCompleto.add(corrida);

        System.out.println("=== Exercícios Individuais ===");
        System.out.println(supino);
        System.out.println(puxada);
        System.out.println(corrida);

        System.out.println("\n=== Treino Superior ===");
        System.out.println("Nome: " + treinoSuperior.getNome());
        System.out.println("Categorias: " + treinoSuperior.getCategoria());
        System.out.println("Grupos Musculares: " + treinoSuperior.getGruposMusculares());

        System.out.println("\n=== Treino Completo ===");
        System.out.println("Nome: " + treinoCompleto.getNome());
        System.out.println("Categorias: " + treinoCompleto.getCategoria());
        System.out.println("Grupos Musculares: " + treinoCompleto.getGruposMusculares());

        System.out.println("\n=== Removendo Corrida ===");
        treinoCompleto.remove(corrida);

        System.out.println("Categorias: " + treinoCompleto.getCategoria());
        System.out.println("Grupos Musculares: " + treinoCompleto.getGruposMusculares());
    }
}