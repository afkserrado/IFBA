package ifba.inf011.facade.rg;

// Subsystem
public class CodecFactory {
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if ("mp4".equalsIgnoreCase(type)) {
            System.out.println("CodecFactory: extracting mpeg audio...");
            return new MPEG4CompressionCodec();
        } else {
            System.out.println("CodecFactory: extracting ogg audio...");
            return new OggCompressionCodec();
        }
    }
}