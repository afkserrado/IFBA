package ifba.inf011.facade;

import java.io.File;

import ifba.inf011.facade.rg.VideoConversionFacade;

// Client
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Implementação 1: Fachada simples de conversão de vídeo ===");
        File convertedFile = new VideoConversionFacade()
                .convertVideo("funny-cats-video.ogg", "mp4");
        System.out.println("Arquivo gerado: " + convertedFile.getPath());
    }
}