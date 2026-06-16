package ifba.inf011.p2.s26_1.domain.track;

public class VideoTrack {

    private String streamName;
    private int durationInSeconds;

    public VideoTrack(String streamName) {
        this(streamName, 0);
    }

    public VideoTrack(String streamName, int durationInSeconds) {
        this.streamName = streamName;
        this.durationInSeconds = durationInSeconds;
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

    public String render() {
        return "[VideoTrack] "
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