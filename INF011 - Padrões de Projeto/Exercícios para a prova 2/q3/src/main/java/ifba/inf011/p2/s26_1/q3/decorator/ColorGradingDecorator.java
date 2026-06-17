package ifba.inf011.p2.s26_1.q3.decorator;

import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Concrete Decorator
public class ColorGradingDecorator extends VideoTrackBaseDecorator {

    private String perfilCor;

    public ColorGradingDecorator(VideoTrack inner, String perfilCor) {
        super(inner);
        this.perfilCor = perfilCor;
    }

    @Override
    public String render() {
        return super.render()
                + "\n[Efeito] Color Grading aplicado: "
                + this.perfilCor;
    }
}