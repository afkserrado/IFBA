package ifba.inf011.p2.s26_1.q8;

import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q8.facade.VideoExportFacade;

public class Main {

    public static void main(String[] args) {

        VideoExportFacade facade = new VideoExportFacade();

        facade.addVideoTrack(new VideoTrack("filme_cena01.mov", 120));
        facade.addAudioTrack(new AudioTrack("audio_master.wav", 120));

        SubTitleTrack legenda = new SubTitleTrack("legenda_pt.srt", "Português");
        legenda.addSubtitle("Exportação simplificada com Facade.");
        facade.addSubTitleTrack(legenda);

        facade.exportarCinema("saida_cinema.mov");
        facade.exportarRedeSocial("saida_rede_social.mp4");

        System.out.println("Exportações concluídas.");
    }
}