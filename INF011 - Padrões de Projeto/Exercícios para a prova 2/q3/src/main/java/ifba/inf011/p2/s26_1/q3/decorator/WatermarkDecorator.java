package ifba.inf011.p2.s26_1.q3.decorator;

import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Concrete Decorator
public class WatermarkDecorator extends VideoTrackBaseDecorator {

    private String texto;

    public WatermarkDecorator(VideoTrack inner, String texto) {
        super(inner);
        this.texto = texto;
    }

    @Override
    public String render() {
        return super.render()
                + "\n[Efeito] Watermark aplicado: "
                + this.texto;
    }
}