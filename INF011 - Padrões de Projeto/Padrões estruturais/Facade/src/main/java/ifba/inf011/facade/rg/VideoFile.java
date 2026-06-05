package ifba.inf011.facade.rg;

// Subsystem
public class VideoFile {
    private final String name;
    private final String codecType;

    public VideoFile(String name) {
        this.name = name;
        int dotIndex = name.lastIndexOf('.');
        this.codecType = dotIndex >= 0 ? name.substring(dotIndex + 1) : ""; // Extensão do file
    }

    public String getCodecType() {
        return codecType;
    }

    public String getName() {
        return name;
    }
}