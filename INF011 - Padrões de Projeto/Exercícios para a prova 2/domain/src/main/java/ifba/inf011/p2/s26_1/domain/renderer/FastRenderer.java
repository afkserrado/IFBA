package ifba.inf011.p2.s26_1.domain.renderer;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

public class FastRenderer extends AbstractRenderer {

    @Override
    public String getRendererName() {
        return "FastRenderer";
    }

    @Override
    public String render(Timeline timeline) {
        this.checkInitialized();

        StringBuilder sb = new StringBuilder();

        sb.append("[FastRenderer]\n");
        sb.append("Renderização rápida\n");
        sb.append("Canva: ").append(this.targetCanva.getResolution()).append("\n");

        sb.append("\n--- VIDEO TRACKS ---\n");
        for (VideoTrack track : timeline.getVideoTracks()) {
            sb.append(track.render()).append("\n");
        }

        return sb.toString();
    }
}