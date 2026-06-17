package ifba.inf011.p2.s26_1.q2.decorator;

import ifba.inf011.p2.s26_1.q2.composite.ClipComponent;

// Concrete Decorator
public class FiltroCorClip extends ClipBaseDecorator {

    private String filtro;

    public FiltroCorClip(ClipComponent inner, String filtro) {
        super(inner);
        this.filtro = filtro;
    }

    @Override
    public String render() {
        return super.render()
                + "\n[Efeito] Filtro de cor aplicado: "
                + this.filtro;
    }
}