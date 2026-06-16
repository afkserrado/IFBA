package ifba.inf011.p2.s26_1.domain.renderer;

import ifba.inf011.p2.s26_1.domain.canva.Canva;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

public interface Renderer {

    public void initialize(Canva target);
    public String render(Timeline timeline);
    public Canva getTargetCanva();
    public String getRendererName();
}