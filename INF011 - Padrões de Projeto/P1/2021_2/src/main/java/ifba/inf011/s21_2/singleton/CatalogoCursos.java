package ifba.inf011.s21_2.singleton;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.s21_2.concrete_products.Curso;

// Singleton
public class CatalogoCursos {

    private static CatalogoCursos instance;
    private Map<String, Curso> catalogo;

    private CatalogoCursos() {
        this.catalogo = new HashMap<>();
    }

    public static CatalogoCursos getInstance() {
        if (instance == null) {
            instance = new CatalogoCursos();
        }
        return instance;
    }

    public void registrarCurso(Curso curso) {
        this.catalogo.put(curso.getNome(), curso);
    }

    public Curso recuperarCurso(String nome) {
        Curso prototipo = this.catalogo.get(nome);

        if (prototipo == null) {
            throw new IllegalArgumentException("Curso não encontrado no catálogo: " + nome);
        }

        return prototipo.clone();
    }
}