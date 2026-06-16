package ifba.inf011.p2.s26_1.domain.encoder;

public enum EncoderProfile {

    CINEMA_PRORES(
            "MOV (Apple QuickTime)",
            "ProRes 422 HQ",
            "PCM Linear",
            "Lossless (Sem Perdas)"
    ),

    NETWORKS_AAC(
            "MP4 (MPEG-4 Part 14)",
            "H.264 / AVC",
            "AAC-LC",
            "Lossy (Alta Compressão)"
    ),

    STANDARD_H265(
            "MKV (Matroska)",
            "H.265 / HEVC",
            "Opus",
            "Balanced (Eficiente)"
    );

    private final String containerFormat;
    private final String videoCodec;
    private final String audioCodec;
    private final String compressionType;

    EncoderProfile(
            String containerFormat,
            String videoCodec,
            String audioCodec,
            String compressionType
    ) {
        this.containerFormat = containerFormat;
        this.videoCodec = videoCodec;
        this.audioCodec = audioCodec;
        this.compressionType = compressionType;
    }

    public String getContainerFormat() {
        return this.containerFormat;
    }

    public String getVideoCodec() {
        return this.videoCodec;
    }

    public String getAudioCodec() {
        return this.audioCodec;
    }

    public String getCompressionType() {
        return this.compressionType;
    }
}