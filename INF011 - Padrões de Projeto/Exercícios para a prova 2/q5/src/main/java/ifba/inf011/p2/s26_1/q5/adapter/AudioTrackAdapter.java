package ifba.inf011.p2.s26_1.q5.adapter;

import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.q5.composite.TrackComponent;

// Leaf + Adapter
public class AudioTrackAdapter implements TrackComponent {

    private AudioTrack audioTrack;

    public AudioTrackAdapter(AudioTrack audioTrack) {
        this.audioTrack = audioTrack;
    }

    @Override
    public int getDuracao() {
        return this.audioTrack.getDurationInSeconds();
    }

    @Override
    public String render() {
        return this.audioTrack.render();
    }

    public AudioTrack getAudioTrack() {
        return this.audioTrack;
    }
}