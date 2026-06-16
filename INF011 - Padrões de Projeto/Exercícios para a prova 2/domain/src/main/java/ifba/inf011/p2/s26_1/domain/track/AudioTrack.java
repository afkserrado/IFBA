package ifba.inf011.p2.s26_1.domain.track;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AudioTrack {

    private String streamName;
    private int durationInSeconds;
    private byte[] content;

    public AudioTrack(String streamName) {
        this(streamName, 0);
    }

    public AudioTrack(String streamName, int durationInSeconds) {
        this.streamName = streamName;
        this.durationInSeconds = durationInSeconds;
        this.content = ("AUDIO_CONTENT:" + streamName).getBytes(StandardCharsets.UTF_8);
    }

    public String getStreamName() {
        return this.streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public int getDurationInSeconds() {
        return this.durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        if (durationInSeconds < 0) {
            throw new IllegalArgumentException("A duração não pode ser negativa.");
        }

        this.durationInSeconds = durationInSeconds;
    }

    public byte[] getContent() {
        return Arrays.copyOf(this.content, this.content.length);
    }

    public void setContent(byte[] content) {
        if (content == null) {
            this.content = new byte[0];
            return;
        }

        this.content = Arrays.copyOf(content, content.length);
    }

    public String render() {
        return "[AudioTrack] "
                + this.streamName
                + " | duração: "
                + this.durationInSeconds
                + "s";
    }

    @Override
    public String toString() {
        return this.render();
    }
}