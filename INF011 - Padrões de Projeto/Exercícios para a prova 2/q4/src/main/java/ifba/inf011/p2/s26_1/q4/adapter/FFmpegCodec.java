package ifba.inf011.p2.s26_1.q4.adapter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

// Service / Adaptee
public class FFmpegCodec {

    private String format;

    public void initialize(String format) {
        this.format = format;
    }

    public byte[] compress(String[] tracks, Map<String, String> options) {
        StringBuilder sb = new StringBuilder();

        sb.append("[FFmpegCodec]\n");
        sb.append("Formato: ").append(this.format).append("\n");
        sb.append("Options: ").append(options).append("\n");

        for (String track : tracks) {
            sb.append(track).append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    public void release() {
        this.format = null;
    }
}