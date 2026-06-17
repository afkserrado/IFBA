package ifba.inf011.p2.s26_1.q3;

import java.nio.charset.StandardCharsets;

import ifba.inf011.p2.s26_1.domain.track.AudioTrack;
import ifba.inf011.p2.s26_1.domain.track.VideoTrack;
import ifba.inf011.p2.s26_1.q3.decorator.ColorGradingDecorator;
import ifba.inf011.p2.s26_1.q3.decorator.WatermarkDecorator;
import ifba.inf011.p2.s26_1.q3.proxy.AccessDeniedException;
import ifba.inf011.p2.s26_1.q3.proxy.AudioTrackProtegidaProxy;
import ifba.inf011.p2.s26_1.q3.proxy.Credencial;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== Decorator =====\n");

        VideoTrack video = new VideoTrack("MainShot_4K.mov", 120);

        VideoTrack videoDecorado =
                new WatermarkDecorator(
                    new ColorGradingDecorator(
                        video,
                        "Cinema HDR"
                    ),
                    "IFBA Studio"
                );

        System.out.println("===== Decorator em VideoTrack =====");
        System.out.println(videoDecorado.render());
        System.out.println("Duração: " + videoDecorado.getDurationInSeconds() + "s");

        System.out.println("\n===== Proxy =====\n");

        AudioTrack audioReal = new AudioTrack("trilha_licenciada.wav", 180);

        AudioTrack audioComAcesso =
                new AudioTrackProtegidaProxy(
                        audioReal,
                        new Credencial("admin", "123")
                );

        AudioTrack audioSemAcesso =
                new AudioTrackProtegidaProxy(
                        audioReal,
                        new Credencial("usuario", "senha_errada")
                );

        System.out.println("===== Proxy com acesso válido =====");
        byte[] conteudo = audioComAcesso.getContent();
        System.out.println(new String(conteudo, StandardCharsets.UTF_8));

        System.out.println("\n===== Proxy com acesso inválido =====");

        try {
            audioSemAcesso.getContent();
        } catch (AccessDeniedException ex) {
            System.out.println(ex.getMessage());
        }
    }
}