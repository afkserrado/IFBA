package ifba.inf011.p2.s26_1.q1.bridge;

import java.util.List;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Concrete Implementor
public class NivelCompleto implements NivelConteudo {
    
    @Override
    public String gerar(Timeline dados) {
        
        List<AudioTrack> audios = dados.getAudioTracks();
        List<VideoTrack> videos = dados.getVideoTracks();
        List<SubTitleTrack> subtitles = dados.getSubtitleTracks();

        StringBuilder sb = new StringBuilder();

        for(VideoTrack video : videos) {
            sb.append(video.toString());
            sb.append("\n");
        }

        for(AudioTrack audio : audios) {
            sb.append(audio.toString());
            sb.append("\n");
        }

        for(SubTitleTrack subtitle : subtitles) {
            sb.append(subtitle.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
