package ifba.inf011.p2.s26_1.q5;

import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q5.adapter.AudioTrackAdapter;
import ifba.inf011.p2.s26_1.q5.adapter.SubTitleTrackAdapter;
import ifba.inf011.p2.s26_1.q5.adapter.VideoTrackAdapter;
import ifba.inf011.p2.s26_1.q5.composite.TrackGroup;

public class Main {

    public static void main(String[] args) {

        TrackGroup cena01 = new TrackGroup("Cena 01");

        cena01.adicionar(
                new VideoTrackAdapter(
                        new VideoTrack("cena01_video.mov", 90)
                )
        );

        cena01.adicionar(
                new AudioTrackAdapter(
                        new AudioTrack("cena01_audio.wav", 95)
                )
        );

        SubTitleTrack legenda = new SubTitleTrack("legendas_cena01.srt", "Português");
        legenda.addSubtitle("Primeira fala da cena.");
        legenda.addSubtitle("Segunda fala da cena.");

        cena01.adicionar(new SubTitleTrackAdapter(legenda, 95));

        TrackGroup flashback = new TrackGroup("Flashback");

        flashback.adicionar(
                new VideoTrackAdapter(
                        new VideoTrack("flashback_video.mov", 35)
                )
        );

        flashback.adicionar(
                new AudioTrackAdapter(
                        new AudioTrack("flashback_audio.wav", 40)
                )
        );

        cena01.adicionar(flashback);

        System.out.println("===== Composite + Adapter =====");
        System.out.println(cena01.render());
        System.out.println("Duração da Cena 01: " + cena01.getDuracao() + "s");
    }
}