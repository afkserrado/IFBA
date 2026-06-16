package ifba.inf011.p2.s26_1.q1.bridge;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

// Interface Abstraction
public interface Exportador {
    public String exportar(Timeline dados);
    public NivelConteudo getNivel();
    public void setNivel(NivelConteudo nivel);
}
