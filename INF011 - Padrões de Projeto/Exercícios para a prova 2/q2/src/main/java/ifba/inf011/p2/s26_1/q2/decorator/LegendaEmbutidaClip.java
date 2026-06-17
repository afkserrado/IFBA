package ifba.inf011.p2.s26_1.q2.decorator;

import ifba.inf011.p2.s26_1.q2.composite.ClipComponent;

// Concrete Decorator
public class LegendaEmbutidaClip extends ClipBaseDecorator {

    private String legenda;

    public LegendaEmbutidaClip(ClipComponent inner, String legenda) {
        super(inner);
        this.legenda = legenda;
    }

    @Override
    public String render() {
        return super.render()
                + "\n[Efeito] Legenda embutida: "
                + this.legenda;
    }
}