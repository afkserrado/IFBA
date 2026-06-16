package ifba.inf011.p2.s26_1.q1.bridge;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

// Interface Implementor do Bridge
// Interface Component do Decorator
public interface NivelConteudo {
    public String gerar(Timeline dados);
}
