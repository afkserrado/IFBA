package ifba.inf011.p2.s26_1.q6.renderer;

import ifba.inf011.p2.s26_1.q6.canvas.Canva;

// Abstract Abstraction do Bridge
public abstract class AbstractRenderer implements Renderer {

    protected Canva canva;

    public AbstractRenderer(Canva canva) {
        this.canva = canva;
    }

    @Override
    public Canva getCanva() {
        return this.canva;
    }

    @Override
    public void setCanva(Canva canva) {
        this.canva = canva;
    }

    protected String dadosCanva() {
        return "Canva: "
                + this.canva.getResolution()
                + " | "
                + this.canva.getWidth()
                + "x"
                + this.canva.getHeight()
                + " | proporção "
                + this.canva.getAspectRatio()
                + " | espaço de cor "
                + this.canva.getColorSpace()
                + " | HDR="
                + this.canva.isHDRSupported();
    }

    protected void checkCanva() {
        if (this.canva == null) {
            throw new IllegalStateException("Renderer sem Canva associado.");
        }
    }
}