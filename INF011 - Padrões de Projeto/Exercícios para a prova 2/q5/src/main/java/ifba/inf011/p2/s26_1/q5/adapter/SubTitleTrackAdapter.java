package ifba.inf011.p2.s26_1.q5.adapter;

import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.q5.composite.TrackComponent;

// Leaf + Adapter
public class SubTitleTrackAdapter implements TrackComponent {

    private SubTitleTrack subtitleTrack;
    private int duracao;

    public SubTitleTrackAdapter(SubTitleTrack subtitleTrack, int duracao) {
        this.subtitleTrack = subtitleTrack;
        this.duracao = duracao;
    }

    @Override
    public int getDuracao() {
        return this.duracao;
    }

    @Override
    public String render() {
        return this.subtitleTrack.render();
    }

    public SubTitleTrack getSubtitleTrack() {
        return this.subtitleTrack;
    }
}