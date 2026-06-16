package ifba.inf011.p2.s26_1.q1.bridge;

// Abstract Abstraction
public abstract class AbstractExportador implements Exportador {
    
    protected NivelConteudo nivel;

    public AbstractExportador(NivelConteudo nivel) {
        this.nivel = nivel;
    }

    @Override
    public NivelConteudo getNivel() {
        return nivel;
    }

    @Override
    public void setNivel(NivelConteudo nivel) {
        this.nivel = nivel;
    }
}
