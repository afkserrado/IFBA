package ifba.inf011.state.contextparameter;

import java.util.ArrayList;
import java.util.List;

// Context
public class PlayerController {

    private ControlState state;
    private List<String> playlist;
    private int currentTrack;
    private boolean playing;

    public PlayerController() {
        this.state = new ReadyControlState();
        this.playlist = new ArrayList<>();
        this.currentTrack = 0;
        this.playing = false;

        for (int i = 1; i <= 5; i++) {
            this.playlist.add("Track " + i);
        }
    }

    public void setState(ControlState state) {
        this.state = state;
    }

    public String lock() {
        return this.state.pressLock(this);
    }

    public String play() {
        return this.state.pressPlay(this);
    }

    public String next() {
        return this.state.pressNext(this);
    }

    public String previous() {
        return this.state.pressPrevious(this);
    }

    public String startPlayback() {
        this.playing = true;
        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public String pausePlayback() {
        this.playing = false;
        return "Reprodução pausada";
    }

    public String nextTrack() {
        this.currentTrack = (this.currentTrack + 1) % this.playlist.size();
        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public String previousTrack() {
        this.currentTrack--;

        if (this.currentTrack < 0) {
            this.currentTrack = this.playlist.size() - 1;
        }

        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public String getStateName() {
        return this.state.name();
    }
}