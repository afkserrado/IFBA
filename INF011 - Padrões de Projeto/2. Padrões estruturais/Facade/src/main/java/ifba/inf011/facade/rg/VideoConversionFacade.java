package ifba.inf011.facade.rg;

import java.io.File;

// Facade
public class VideoConversionFacade {
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: conversion started.");

        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);

        Codec destinationCodec;
        if ("mp4".equalsIgnoreCase(format)) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }

        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        File result = new AudioMixer().fix(intermediateResult);

        System.out.println("VideoConversionFacade: conversion completed.");
        return result;
    }
}