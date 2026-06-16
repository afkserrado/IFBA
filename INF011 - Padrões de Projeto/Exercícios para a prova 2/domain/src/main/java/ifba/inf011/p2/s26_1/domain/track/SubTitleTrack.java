package ifba.inf011.p2.s26_1.domain.track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubTitleTrack {

    private String streamName;
    private String idioma;
    private List<String> subtitles;

    public SubTitleTrack(String streamName, String idioma) {
        this.streamName = streamName;
        this.idioma = idioma;
        this.subtitles = new ArrayList<String>();
    }

    public String getStreamName() {
        return this.streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void addSubtitle(String subtitle) {
        this.subtitles.add(subtitle);
    }

    public List<String> getSubtitles() {
        return Collections.unmodifiableList(this.subtitles);
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("[SubTitleTrack] ")
          .append(this.streamName)
          .append(" | idioma: ")
          .append(this.idioma);

        if (!this.subtitles.isEmpty()) {
            sb.append("\n");

            for (String subtitle : this.subtitles) {
                sb.append(" - ").append(subtitle).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.render();
    }
}