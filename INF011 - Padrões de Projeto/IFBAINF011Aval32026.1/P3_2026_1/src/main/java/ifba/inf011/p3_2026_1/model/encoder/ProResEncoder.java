package ifba.inf011.p3_2026_1.model.encoder;

import ifba.inf011.p3_2026_1.model.renderer.Renderer;

public class ProResEncoder implements Encoder {

	private Renderer target;
    private final EncoderProfile profile = EncoderProfile.CINEMA_PRORES;
    private String outputPath;

    @Override
    public void initialize(Renderer target) {
    	this.target = target;
    }

    @Override
    public void setupContainer(String outputPath) {
        this.outputPath = outputPath;
    }

}