package ifba.inf011.p3_2026_1.model.encoder;

import ifba.inf011.p3_2026_1.model.renderer.Renderer;

public interface Encoder{
    void initialize(Renderer target);
    void setupContainer(String outputPath);
}