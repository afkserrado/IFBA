package ifba.inf011.p2.s26_1.q3.decorator;

import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Base Decorator
public abstract class VideoTrackBaseDecorator extends VideoTrack {

    protected VideoTrack inner;

    public VideoTrackBaseDecorator(VideoTrack inner) {
        super(inner.getStreamName(), inner.getDurationInSeconds());
        this.inner = inner;
    }

    @Override
    public String render() {
        return this.inner.render();
    }

    @Override
    public String getStreamName() {
        return this.inner.getStreamName();
    }

    @Override
    public void setStreamName(String streamName) {
        this.inner.setStreamName(streamName);
    }

    @Override
    public int getDurationInSeconds() {
        return this.inner.getDurationInSeconds();
    }

    @Override
    public void setDurationInSeconds(int durationInSeconds) {
        this.inner.setDurationInSeconds(durationInSeconds);
    }
}