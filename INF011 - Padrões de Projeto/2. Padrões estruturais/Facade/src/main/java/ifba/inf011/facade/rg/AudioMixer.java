package ifba.inf011.facade.rg;

import java.io.File;

// Subsystem
public class AudioMixer {
    public File fix(VideoFile result) {
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp/" + result.getName());
    }
}