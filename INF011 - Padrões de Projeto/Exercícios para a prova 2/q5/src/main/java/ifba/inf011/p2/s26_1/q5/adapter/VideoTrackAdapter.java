package ifba.inf011.p2.s26_1.q5.adapter;

import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q5.composite.TrackComponent;

// Leaf + Adapter
public class VideoTrackAdapter implements TrackComponent {

    private VideoTrack videoTrack;

    public VideoTrackAdapter(VideoTrack videoTrack) {
        this.videoTrack = videoTrack;
    }

    @Override
    public int getDuracao() {
        return this.videoTrack.getDurationInSeconds();
    }

    @Override
    public String render() {
        return this.videoTrack.render();
    }

    public VideoTrack getVideoTrack() {
        return this.videoTrack;
    }
}