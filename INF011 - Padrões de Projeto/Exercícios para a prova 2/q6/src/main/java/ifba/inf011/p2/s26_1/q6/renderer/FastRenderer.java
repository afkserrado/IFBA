package ifba.inf011.p2.s26_1.q6.renderer;

import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q6.canvas.Canva;

// Refined Abstraction do Bridge
public class FastRenderer extends AbstractRenderer {

    public FastRenderer(Canva canva) {
        super(canva);
    }

    @Override
    public String render(Timeline timeline) {
        this.checkCanva();

        StringBuilder sb = new StringBuilder();

        sb.append("[FastRenderer]\n");
        sb.append(this.dadosCanva()).append("\n");
        sb.append("Renderização rápida apenas das VideoTracks.\n");

        for (VideoTrack video : timeline.getVideoTracks()) {
            sb.append(video.render()).append("\n");
        }

        return sb.toString();
    }
}