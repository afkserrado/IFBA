package ifba.inf011.p2.s26_1.q8.facade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ifba.inf011.p2.s26_1.domain.canva.Canva4K;
import ifba.inf011.p2.s26_1.domain.canva.VerticalCanva;
import ifba.inf011.p2.s26_1.domain.encoder.NetworksAACEncoder;
import ifba.inf011.p2.s26_1.domain.encoder.ProResEncoder;
import ifba.inf011.p2.s26_1.domain.renderer.FastRenderer;
import ifba.inf011.p2.s26_1.domain.renderer.HighPrecisionRenderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Facade
public class VideoExportFacade {

    private Timeline timeline;

    public VideoExportFacade() {
        this.timeline = new Timeline();
    }

    public void addVideoTrack(VideoTrack track) {
        this.timeline.addVideoTrack(track);
    }

    public void addAudioTrack(AudioTrack track) {
        this.timeline.addAudioTrack(track);
    }

    public void addSubTitleTrack(SubTitleTrack track) {
        this.timeline.addSubTitleTrack(track);
    }

    public void exportarCinema(String outputPath) {
        this.timeline.setCanva(new Canva4K());
        this.timeline.setRenderer(new HighPrecisionRenderer());
        this.timeline.setEncoder(new ProResEncoder());

        byte[] conteudo = this.timeline.export(outputPath);
        this.salvarArquivo(outputPath, conteudo);
    }

    public void exportarRedeSocial(String outputPath) {
        this.timeline.setCanva(new VerticalCanva());
        this.timeline.setRenderer(new FastRenderer());
        this.timeline.setEncoder(new NetworksAACEncoder());

        byte[] conteudo = this.timeline.export(outputPath);
        this.salvarArquivo(outputPath, conteudo);
    }

    private void salvarArquivo(String outputPath, byte[] conteudo) {
        try {
            Files.write(Path.of(outputPath), conteudo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo exportado: " + outputPath, e);
        }
    }
}