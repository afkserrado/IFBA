package ifba.inf011.p3_2026_1.model.renderer;

import ifba.inf011.p3_2026_1.model.canva.Canva;

public interface Renderer {
    public void initialize(Canva target);
    public void render();
}