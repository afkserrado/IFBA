package ifba.inf011.p2.s26_1.domain.encoder;

import java.nio.charset.StandardCharsets;

import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;

public abstract class AbstractEncoder implements Encoder {

    protected Renderer target;
    protected EncoderProfile profile;
    protected String outputPath;

    public AbstractEncoder(EncoderProfile profile) {
        this.profile = profile;
    }

    @Override
    public void initialize(Renderer target) {
        this.target = target;

        System.out.println("[" + this.getEncoderName() + "] Conectado ao renderer: "
                + target.getRendererName());

        System.out.println("[" + this.getEncoderName() + "] Codec de vídeo: "
                + this.profile.getVideoCodec());

        System.out.println("[" + this.getEncoderName() + "] Codec de áudio: "
                + this.profile.getAudioCodec());
    }

    @Override
    public void setupContainer(String outputPath) {
        this.outputPath = outputPath;

        System.out.println("[" + this.getEncoderName() + "] Criando container: "
                + this.profile.getContainerFormat());

        System.out.println("[" + this.getEncoderName() + "] Arquivo de saída: "
                + this.outputPath);
    }

    @Override
    public byte[] encode(Timeline timeline) {
        this.checkInitialized();

        String rendered = this.target.render(timeline);

        String encoded =
                "[" + this.getEncoderName() + "]\n"
                        + "Container: " + this.profile.getContainerFormat() + "\n"
                        + "Video Codec: " + this.profile.getVideoCodec() + "\n"
                        + "Audio Codec: " + this.profile.getAudioCodec() + "\n"
                        + "Compression: " + this.profile.getCompressionType() + "\n"
                        + "Output: " + this.outputPath + "\n\n"
                        + rendered;

        return encoded.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Renderer getTargetRenderer() {
        return this.target;
    }

    @Override
    public String getOutputPath() {
        return this.outputPath;
    }

    protected void checkInitialized() {
        if (this.target == null) {
            throw new IllegalStateException("Encoder não foi inicializado com um Renderer.");
        }

        if (this.outputPath == null || this.outputPath.isBlank()) {
            throw new IllegalStateException("Container de saída não foi configurado.");
        }
    }
}