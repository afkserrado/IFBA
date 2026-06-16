package ifba.inf011.p2.s26_1.domain.encoder;

import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

public interface Encoder {

    public void initialize(Renderer target);
    public void setupContainer(String outputPath);
    public byte[] encode(Timeline timeline);
    public Renderer getTargetRenderer();
    public String getOutputPath();
    public String getEncoderName();
}