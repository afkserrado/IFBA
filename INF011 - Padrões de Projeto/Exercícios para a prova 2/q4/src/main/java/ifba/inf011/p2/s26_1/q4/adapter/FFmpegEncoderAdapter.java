package ifba.inf011.p2.s26_1.q4.adapter;

import java.util.HashMap;
import java.util.Map;

import ifba.inf011.p2.s26_1.domain.encoder.Encoder;
import ifba.inf011.p2.s26_1.domain.renderer.Renderer;
import ifba.inf011.p2.s26_1.domain.timeline.Timeline;
import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.SubTitleTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;

// Adapter
public class FFmpegEncoderAdapter implements Encoder {

    private FFmpegCodec codec;
    private Renderer renderer;
    private String outputPath;
    private String format;

    public FFmpegEncoderAdapter(FFmpegCodec codec, String format) {
        this.codec = codec;
        this.format = format;
    }

    @Override
    public void initialize(Renderer target) {
        this.renderer = target;
        this.codec.initialize(this.format);
    }

    @Override
    public void setupContainer(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public byte[] encode(Timeline timeline) {
        String[] tracks = this.extractTracks(timeline);

        Map<String, String> options = new HashMap<String, String>();
        options.put("outputPath", this.outputPath);
        options.put("renderer", this.renderer.getRendererName());
        options.put("format", this.format);

        byte[] encoded = this.codec.compress(tracks, options);

        this.codec.release();

        return encoded;
    }

    private String[] extractTracks(Timeline timeline) {
        int total =
                timeline.getVideoTracks().size()
              + timeline.getAudioTracks().size()
              + timeline.getSubtitleTracks().size();

        String[] tracks = new String[total];

        int i = 0;

        for (VideoTrack video : timeline.getVideoTracks()) {
            tracks[i++] = video.render();
        }

        for (AudioTrack audio : timeline.getAudioTracks()) {
            tracks[i++] = audio.render();
        }

        for (SubTitleTrack subtitle : timeline.getSubtitleTracks()) {
            tracks[i++] = subtitle.render();
        }

        return tracks;
    }

    @Override
    public Renderer getTargetRenderer() {
        return this.renderer;
    }

    @Override
    public String getOutputPath() {
        return this.outputPath;
    }

    @Override
    public String getEncoderName() {
        return "FFmpegEncoderAdapter";
    }
}