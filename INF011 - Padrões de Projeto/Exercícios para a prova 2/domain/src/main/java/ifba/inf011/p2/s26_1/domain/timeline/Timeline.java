package ifba.inf011.p2.s26_1.domain.timeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifba.inf011.p2.s26_1.domain.canva.Canva;
import ifba.inf011.p2.s26_1.domain.encoder.Encoder;
import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

public class Timeline {

    private Canva canva;
    private Renderer renderer;
    private Encoder encoder;

    private List<VideoTrack> videoTracks;
    private List<AudioTrack> audioTracks;
    private List<SubTitleTrack> subtitleTracks;

    public Timeline(Canva canva, Renderer renderer, Encoder encoder) {
        this.videoTracks = new ArrayList<VideoTrack>();
        this.audioTracks = new ArrayList<AudioTrack>();
        this.subtitleTracks = new ArrayList<SubTitleTrack>();

        this.setCanva(canva);
        this.setRenderer(renderer);
        this.setEncoder(encoder);
    }

    public Canva getCanva() {
        return this.canva;
    }

    public void setCanva(Canva canva) {
        if (canva == null) {
            throw new IllegalArgumentException("Canva não pode ser nulo.");
        }

        this.canva = canva;

        if (this.renderer != null) {
            this.renderer.initialize(this.canva);
        }
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public void setRenderer(Renderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer não pode ser nulo.");
        }

        this.renderer = renderer;

        if (this.canva != null) {
            this.renderer.initialize(this.canva);
        }

        if (this.encoder != null) {
            this.encoder.initialize(this.renderer);
        }
    }

    public Encoder getEncoder() {
        return this.encoder;
    }

    public void setEncoder(Encoder encoder) {
        if (encoder == null) {
            throw new IllegalArgumentException("Encoder não pode ser nulo.");
        }

        this.encoder = encoder;

        if (this.renderer != null) {
            this.encoder.initialize(this.renderer);
        }
    }

    public void addVideoTrack(VideoTrack track) {
        if (track == null) {
            throw new IllegalArgumentException("VideoTrack não pode ser nula.");
        }

        this.videoTracks.add(track);
    }

    public void addAudioTrack(AudioTrack track) {
        if (track == null) {
            throw new IllegalArgumentException("AudioTrack não pode ser nula.");
        }

        this.audioTracks.add(track);
    }

    public void addSubTitleTrack(SubTitleTrack track) {
        if (track == null) {
            throw new IllegalArgumentException("SubTitleTrack não pode ser nula.");
        }

        this.subtitleTracks.add(track);
    }

    public List<VideoTrack> getVideoTracks() {
        return Collections.unmodifiableList(this.videoTracks);
    }

    public List<AudioTrack> getAudioTracks() {
        return Collections.unmodifiableList(this.audioTracks);
    }

    public List<SubTitleTrack> getSubtitleTracks() {
        return Collections.unmodifiableList(this.subtitleTracks);
    }

    public int getDurationInSeconds() {
        int max = 0;

        for (VideoTrack track : this.videoTracks) {
            max = Math.max(max, track.getDurationInSeconds());
        }

        for (AudioTrack track : this.audioTracks) {
            max = Math.max(max, track.getDurationInSeconds());
        }

        return max;
    }

    public String render() {
        return this.renderer.render(this);
    }

    public byte[] export(String outputPath) {
        this.encoder.setupContainer(outputPath);
        return this.encoder.encode(this);
    }

    @Override
    public String toString() {
        return "Timeline ["
                + "canva=" + this.canva
                + ", renderer=" + this.renderer.getRendererName()
                + ", encoder=" + this.encoder.getEncoderName()
                + ", videoTracks=" + this.videoTracks.size()
                + ", audioTracks=" + this.audioTracks.size()
                + ", subtitleTracks=" + this.subtitleTracks.size()
                + "]";
    }
}