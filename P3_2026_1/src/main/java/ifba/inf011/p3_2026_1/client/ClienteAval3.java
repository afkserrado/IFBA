package ifba.inf011.p3_2026_1.client;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.TimelineBuilder;
import ifba.inf011.p3_2026_1.avaliacao3.builder.ConcretePacoteBuilder;
import ifba.inf011.p3_2026_1.avaliacao3.builder.PacoteBuilder;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorExportadorXML;
import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorLarguraBanda;
import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Playlist;
import ifba.inf011.p3_2026_1.model.playlist.Video;

public class ClienteAval3 {

    public void runQuestaoI() {
        
        TimelineBuilder timelineBuilder = new CinemaTimelineBuilder();
        PacoteBuilder pacoteBuilder = new ConcretePacoteBuilder();

        Filme matrix = new Filme(
            "Matrix",
            20.0,
            criarTimeline(timelineBuilder, "matrix.mov")
        );

        Filme matrixReloaded = new Filme(
            "Matrix Reloaded",
            25.0,
            criarTimeline(timelineBuilder, "matrix_reloaded.mov")
        );

        Filme matrixRevolutions = new Filme(
            "Matrix Revolutions",
            25.0,
            criarTimeline(timelineBuilder, "matrix_revolutions.mov")
        );

        Filme documentarioMatrix = new Filme(
            "The Matrix Revisited",
            15.0,
            criarTimeline(timelineBuilder, "matrix_revisited.mov")
        );

        pacoteBuilder
            .reset()
            .definirTitulo("Trilogia Matrix")
            .definirDesconto(10.0)
            .adicionarProduto(matrix)
            .adicionarProduto(matrixReloaded)
            .adicionarProduto(matrixRevolutions)
            .adicionarProduto(documentarioMatrix);

        Pacote trilogiaMatrixAvulsa = pacoteBuilder.build();

        Pacote trilogiaMatrixSemDesconto = pacoteBuilder.build();
        trilogiaMatrixSemDesconto.alterarDesconto(0.0);

        Filme starWarsIV = new Filme(
            "Star Wars: Uma Nova Esperança",
            25.0,
            criarTimeline(timelineBuilder, "star_wars_iv.mov")
        );

        Filme starWarsV = new Filme(
            "Star Wars: O Império Contra-Ataca",
            25.0,
            criarTimeline(timelineBuilder, "star_wars_v.mov")
        );

        Filme starWarsVI = new Filme(
            "Star Wars: O Retorno de Jedi",
            25.0,
            criarTimeline(timelineBuilder, "star_wars_vi.mov")
        );

        Pacote pacoteStarWars = pacoteBuilder
            .reset()
            .definirTitulo("Trilogia Star Wars")
            .definirDesconto(0.0)
            .adicionarProduto(starWarsIV)
            .adicionarProduto(starWarsV)
            .adicionarProduto(starWarsVI)
            .build();

        Episodio episodio1 = new Episodio(
            "The National Anthem",
            10.0,
            1,
            criarTimeline(timelineBuilder, "black_mirror_ep1.mov")
        );

        Episodio episodio2 = new Episodio(
            "Fifteen Million Merits",
            10.0,
            2,
            criarTimeline(timelineBuilder, "black_mirror_ep2.mov")
        );

        Episodio episodio3 = new Episodio(
            "The Entire History of You",
            10.0,
            3,
            criarTimeline(timelineBuilder, "black_mirror_ep3.mov")
        );

        Serie blackMirror = new Serie(
            "Black Mirror",
            1,
            episodio1,
            episodio2,
            episodio3
        );

        Filme bladeRunner = new Filme(
            "Blade Runner",
            30.0,
            criarTimeline(timelineBuilder, "blade_runner.mov")
        );

        Pacote colecaoSciFi = pacoteBuilder
            .reset()
            .definirTitulo("Coleção Sci-Fi")
            .definirDesconto(10.0)
            .adicionarProduto(trilogiaMatrixSemDesconto)
            .adicionarProduto(pacoteStarWars)
            .adicionarProduto(blackMirror)
            .adicionarProduto(bladeRunner)
            .build();

        System.out.println("=== QUESTÃO I ===");

        System.out.println("\nPacote vendido separadamente:");
        System.out.println("Produto: " + trilogiaMatrixAvulsa.getTitulo());
        System.out.printf("Preço com desconto: R$ %.2f%n", trilogiaMatrixAvulsa.getPreco());

        System.out.println("\nSuperpacote:");
        System.out.println("Produto: " + colecaoSciFi.getTitulo());
        System.out.printf("Preço total com desconto: R$ %.2f%n", colecaoSciFi.getPreco());
        System.out.println("Duração total: " + colecaoSciFi.getDuracao() + " segundos");
    }

    public void runQuestaoII() {
        
        TimelineBuilder timelineBuilder = new CinemaTimelineBuilder();

        Timeline timeline1 = timelineBuilder.reset()
            .addClassAdapterVideo("matrix.mov")
            .addAudio("matrix_audio.wav")
            .build();

        Timeline timeline2 = timelineBuilder.reset()
            .addClassAdapterVideo("star_wars.mov")
            .addAudio("star_wars_audio.wav")
            .build();

        Filme filme = new Filme("Blade Runner", 30.0, timeline1);
        Episodio episodio = new Episodio("The National Anthem", 10.0, 1, timeline2);
        Serie serie = new Serie("Black Mirror", 1, episodio);

        Pacote pacote = new Pacote("Coleção Sci-Fi", 10.0);
        pacote.adicionarProduto(filme);
        pacote.adicionarProduto(serie);

        Playlist playlist = new Playlist();
        playlist.addItem(pacote);
        playlist.addItem(new MP3("Son Of A Gun", 1000));
        playlist.addItem(new Video("Trailer", 500, "https://..."));

        VisitorLarguraBanda visitorBand = new VisitorLarguraBanda(1.5);
        double total = visitorBand.calcularLarguraBanda(playlist);

        VisitorExportadorXML visitorXml = new VisitorExportadorXML();
        String xml = visitorXml.export(playlist);

        System.out.println("\n=== QUESTÃO II ===");
        System.out.println("Consumo de Largura de Banda: " + total);
        System.out.println(xml);
    }

    public void run() {
        runQuestaoI();
        runQuestaoII();
    }

    private static Timeline criarTimeline(
        TimelineBuilder builder,
        String arquivo
    ) {
        return builder
            .reset()
            .addClassAdapterVideo(arquivo)
            .build();
    }

    public static void main(String[] args) {
        new ClienteAval3().run();
    }
}