package ifba.inf011.p2.s26_1.q6.renderer;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.q6.canvas.Canva;

// Abstraction do Bridge
public interface Renderer {

    public String render(Timeline timeline);
    public Canva getCanva();
    public void setCanva(Canva canva);
}