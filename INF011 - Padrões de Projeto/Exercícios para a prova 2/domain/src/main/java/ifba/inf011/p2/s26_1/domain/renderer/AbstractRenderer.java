package ifba.inf011.p2.s26_1.domain.renderer;

import ifba.inf011.p2.s26_1.domain.canva.Canva;

public abstract class AbstractRenderer implements Renderer {

    protected Canva targetCanva;

    @Override
    public void initialize(Canva target) {
        this.targetCanva = target;

        //System.out.println("[" + this.getRendererName() + "] Inicializando motor gráfico...");
        //System.out.println("[" + this.getRendererName() + "] Canva alvo: " + target);
    }

    @Override
    public Canva getTargetCanva() {
        return this.targetCanva;
    }

    protected void checkInitialized() {
        if (this.targetCanva == null) {
            throw new IllegalStateException("Renderer não foi inicializado com um Canva.");
        }
    }
}