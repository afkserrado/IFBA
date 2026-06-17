package ifba.inf011.p2.s26_1.q7;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p2.s26_1.q7.flyweight.AssetVisual;
import ifba.inf011.p2.s26_1.q7.flyweight.AssetVisualFactory;
import ifba.inf011.p2.s26_1.q7.flyweight.OcorrenciaAsset;

public class Main {

    public static void main(String[] args) {

        AssetVisualFactory factory = new AssetVisualFactory();

        byte[] dadosLogo = "DADOS_BINARIOS_PESADOS_DO_LOGO".getBytes(StandardCharsets.UTF_8);

        AssetVisual logo1 = factory.getAsset(
                "Logo IFBA",
                "/assets/logo-ifba.png",
                "4K",
                dadosLogo
        );

        AssetVisual logo2 = factory.getAsset(
                "Logo IFBA",
                "/assets/logo-ifba.png",
                "4K",
                dadosLogo
        );

        AssetVisual logo3 = factory.getAsset(
                "Logo IFBA",
                "/assets/logo-ifba.png",
                "4K",
                dadosLogo
        );

        List<OcorrenciaAsset> ocorrencias = new ArrayList<OcorrenciaAsset>();

        ocorrencias.add(new OcorrenciaAsset(logo1, 0, 5, 100, 100, 1.0, 1.0));
        ocorrencias.add(new OcorrenciaAsset(logo2, 15, 5, 200, 120, 0.7, 0.8));
        ocorrencias.add(new OcorrenciaAsset(logo3, 45, 8, 300, 180, 0.5, 0.6));

        System.out.println("===== Flyweight =====");

        for (OcorrenciaAsset ocorrencia : ocorrencias) {
            System.out.println(ocorrencia.renderizar());
        }

        System.out.println("\nTotal de ocorrências: " + ocorrencias.size());
        System.out.println("Total de assets pesados compartilhados: " + factory.getTotalAssetsCompartilhados());

        System.out.println("\nMesmo objeto compartilhado?");
        System.out.println(logo1 == logo2);
        System.out.println(logo2 == logo3);
    }
}