package ifba.inf011.state.classic;

import java.util.ArrayList;
import java.util.List;

// Context
public class Player {

    private PlayerState state;
    private boolean playing;
    private List<String> playlist;
    private int currentTrack;

    public Player() {
        this.playlist = new ArrayList<>();
        this.currentTrack = 0;
        this.playing = false;

        for (int i = 1; i <= 5; i++) {
            this.playlist.add("Track " + i);
        }

        this.state = new ReadyState(this);
    }

    public void changeState(PlayerState state) {
        this.state = state;
    }

    public String clickLock() {
        return this.state.onLock();
    }

    public String clickPlay() {
        return this.state.onPlay();
    }

    public String clickNext() {
        return this.state.onNext();
    }

    public String clickPrevious() {
        return this.state.onPrevious();
    }

    public String startPlayback() {
        this.playing = true;
        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public String stopPlayback() {
        this.playing = false;
        return "Reprodução pausada";
    }

    public String nextTrack() {
        this.currentTrack++;

        if (this.currentTrack >= this.playlist.size()) {
            this.currentTrack = 0;
        }

        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public String previousTrack() {
        this.currentTrack--;

        if (this.currentTrack < 0) {
            this.currentTrack = this.playlist.size() - 1;
        }

        return "Tocando " + this.playlist.get(this.currentTrack);
    }

    public String fastForward() {
        return "Avançando alguns segundos em " + this.playlist.get(this.currentTrack);
    }

    public String rewind() {
        return "Voltando alguns segundos em " + this.playlist.get(this.currentTrack);
    }

    public void setCurrentTrackAfterStop() {
        this.currentTrack = 0;
        this.playing = false;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public String getStateName() {
        return this.state.name();
    }
}