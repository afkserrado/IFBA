package ifba.inf011.p2.s26_1.q1;

import ifba.inf011.p2.s26_1.domain.canva.Canva;
import ifba.inf011.p2.s26_1.domain.canva.Canva4K;
import ifba.inf011.p2.s26_1.domain.encoder.Encoder;
import ifba.inf011.p2.s26_1.domain.encoder.ProResEncoder;
import ifba.inf011.p2.s26_1.domain.renderer.HighPrecisionRenderer;
import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q1.bridge.Exportador;
import ifba.inf011.p2.s26_1.q1.bridge.MOVExportador;
import ifba.inf011.p2.s26_1.q1.bridge.NivelCompleto;
import ifba.inf011.p2.s26_1.q1.decorator.MarcaDaguaDecorator;
import ifba.inf011.p2.s26_1.q1.decorator.TimecodeDecorator;

public class Main {
    public static void main(String[] args) {
        
        Canva canva = new Canva4K();
        Renderer renderer = new HighPrecisionRenderer();
        Encoder encoder = new ProResEncoder();

        Timeline timeline = new Timeline(canva, renderer, encoder);

        VideoTrack video = new VideoTrack("MainShot_4K.mov", 120);
        AudioTrack audio = new AudioTrack("Soundtrack_Master.wav", 125);

        SubTitleTrack subtitle = new SubTitleTrack("legendas_pt.stt", "Português");
        subtitle.addSubtitle("Olá, mundo.");
        subtitle.addSubtitle("Esta é uma timeline de teste.");

        timeline.addVideoTrack(video);
        timeline.addAudioTrack(audio);
        timeline.addSubTitleTrack(subtitle);

        System.out.println("===== Bridge =====");

        Exportador exportador = new MOVExportador(new NivelCompleto());
        String dadosMOV = exportador.exportar(timeline);

        System.out.println(dadosMOV);

        exportador.setNivel(
            new MarcaDaguaDecorator(
                new TimecodeDecorator(
                    new NivelCompleto()
                )
            )
        );

        System.out.println("\n===== Decorator =====");

        dadosMOV = exportador.exportar(timeline);
        System.out.println(dadosMOV);
    }
}
