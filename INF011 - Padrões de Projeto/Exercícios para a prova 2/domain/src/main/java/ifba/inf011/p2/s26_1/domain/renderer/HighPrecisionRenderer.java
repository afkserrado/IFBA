package ifba.inf011.p2.s26_1.domain.renderer;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

public class HighPrecisionRenderer extends AbstractRenderer {

    @Override
    public String getRendererName() {
        return "HighPrecisionRenderer";
    }

    @Override
    public String render(Timeline timeline) {
        this.checkInitialized();

        StringBuilder sb = new StringBuilder();

        sb.append("[HighPrecisionRenderer]\n");
        sb.append("Renderização em alta precisão\n");
        sb.append("Canva: ").append(this.targetCanva.getResolution()).append("\n");
        sb.append("Color Space: ").append(this.targetCanva.getColorSpace()).append("\n");
        sb.append("HDR: ").append(this.targetCanva.isHDRSupported()).append("\n");

        sb.append("\n--- VIDEO TRACKS ---\n");
        for (VideoTrack track : timeline.getVideoTracks()) {
            sb.append(track.render()).append("\n");
        }

        sb.append("\n--- AUDIO TRACKS ---\n");
        for (AudioTrack track : timeline.getAudioTracks()) {
            sb.append(track.render()).append("\n");
        }

        sb.append("\n--- SUBTITLE TRACKS ---\n");
        for (SubTitleTrack track : timeline.getSubtitleTracks()) {
            sb.append(track.render()).append("\n");
        }

        return sb.toString();
    }
}