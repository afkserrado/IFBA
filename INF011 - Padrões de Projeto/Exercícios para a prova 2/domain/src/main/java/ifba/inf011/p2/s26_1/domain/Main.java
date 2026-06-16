package ifba.inf011.p2.s26_1.domain;

import java.nio.charset.StandardCharsets;

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

public class Main {

    public void run() {
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

        System.out.println("=== TIMELINE ===");
        System.out.println(timeline);

        System.out.println("\n=== RENDER ===");
        System.out.println(timeline.render());

        System.out.println("\n=== EXPORT ===");
        byte[] exported = timeline.export("saida.mov");
        System.out.println(new String(exported, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        new Main().run();
    }
}