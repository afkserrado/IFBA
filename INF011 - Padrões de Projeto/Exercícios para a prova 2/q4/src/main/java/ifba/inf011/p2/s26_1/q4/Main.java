package ifba.inf011.p2.s26_1.q4;

import java.nio.charset.StandardCharsets;

import ifba.inf011.p2.s26_1.domain.canva.Canva;
import ifba.inf011.p2.s26_1.domain.canva.Canva4K;
import ifba.inf011.p2.s26_1.domain.encoder.Encoder;
import ifba.inf011.p2.s26_1.domain.renderer.HighPrecisionRenderer;
import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q4.adapter.FFmpegCodec;
import ifba.inf011.p2.s26_1.q4.adapter.FFmpegEncoderAdapter;

public class Main {

    public static void main(String[] args) {

        Canva canva = new Canva4K();
        Renderer renderer = new HighPrecisionRenderer();

        Encoder encoder = new FFmpegEncoderAdapter(
                new FFmpegCodec(),
                "mp4"
        );

        Timeline timeline = new Timeline(canva, renderer, encoder);

        timeline.addVideoTrack(new VideoTrack("video_principal.mp4", 120));
        timeline.addAudioTrack(new AudioTrack("audio_master.wav", 120));

        SubTitleTrack subtitle = new SubTitleTrack("legendas_pt.srt", "Português");
        subtitle.addSubtitle("Exemplo de legenda.");
        timeline.addSubTitleTrack(subtitle);

        byte[] result = timeline.export("saida.mp4");

        System.out.println(new String(result, StandardCharsets.UTF_8));
    }
}