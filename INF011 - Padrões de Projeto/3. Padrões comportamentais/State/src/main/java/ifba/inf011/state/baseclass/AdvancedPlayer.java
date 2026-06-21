package ifba.inf011.state.baseclass;

import java.util.ArrayList;
import java.util.List;

// Context
public class AdvancedPlayer {

    private ModeState state;
    private List<String> playlist;
    private int currentTrack;
    private boolean playing;

    public AdvancedPlayer() {
        this.state = new ReadyModeState();
        this.playlist = new ArrayList<>();
        this.currentTrack = 0;
        this.playing = false;

        for (int i = 1; i <= 5; i++) {
            this.playlist.add("Track " + i);
        }
    }

    public void lock() {
        this.state = this.state.lock(this);
    }

    public void play() {
        this.state = this.state.play(this);
    }

    public void next() {
        this.state = this.state.next(this);
    }

    public void previous() {
        this.state = this.state.previous(this);
    }

    public void startPlayback() {
        this.playing = true;
        System.out.println("Tocando " + this.playlist.get(this.currentTrack));
    }

    public void pausePlayback() {
        this.playing = false;
        System.out.println("Reprodução pausada");
    }

    public void nextTrack() {
        this.currentTrack = (this.currentTrack + 1) % this.playlist.size();
        System.out.println("Tocando " + this.playlist.get(this.currentTrack));
    }

    public void previousTrack() {
        this.currentTrack--;

        if (this.currentTrack < 0) {
            this.currentTrack = this.playlist.size() - 1;
        }

        System.out.println("Tocando " + this.playlist.get(this.currentTrack));
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public String getStateName() {
        return this.state.name();
    }
}