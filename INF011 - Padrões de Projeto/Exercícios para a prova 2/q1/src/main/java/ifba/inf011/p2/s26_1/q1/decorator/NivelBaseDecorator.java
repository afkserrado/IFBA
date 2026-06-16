package ifba.inf011.p2.s26_1.q1.decorator;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.q1.bridge.NivelConteudo;

// Base Decorator
public abstract class NivelBaseDecorator implements NivelConteudo {
    
    protected NivelConteudo inner;

    public NivelBaseDecorator(NivelConteudo inner) {
        this.inner = inner;
    }

    @Override
    public String gerar(Timeline dados) {
        return inner.gerar(dados);
    }
}
