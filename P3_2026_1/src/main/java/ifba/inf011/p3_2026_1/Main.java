package ifba.inf011.p3_2026_1;

import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.Timeline;
import ifba.inf011.p3_2026_1.avaliacao1.timeline.builder.TimelineBuilder;
import ifba.inf011.p3_2026_1.builder.ConcretePacoteBuilder;
import ifba.inf011.p3_2026_1.builder.PacoteBuilder;
import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;

public class Main {

    public static void main(String[] args) {
        q1();
    }

    public static void q1() {

        TimelineBuilder timelineBuilder = new CinemaTimelineBuilder();
        PacoteBuilder pacoteBuilder = new ConcretePacoteBuilder();

        // Filmes da Trilogia Matrix
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

        // Configuração do Pacote Matrix
        pacoteBuilder
            .reset()
            .definirTitulo("Trilogia Matrix")
            .definirDesconto(10.0)
            .adicionarProduto(matrix)
            .adicionarProduto(matrixReloaded)
            .adicionarProduto(matrixRevolutions)
            .adicionarProduto(documentarioMatrix);

        // Pacote vendido separadamente com 10% de desconto
        Pacote trilogiaMatrixAvulsa = pacoteBuilder.build();

        // Nova instância criada a partir do mesmo estado do builder
        Pacote trilogiaMatrixSemDesconto = pacoteBuilder.build();
        trilogiaMatrixSemDesconto.alterarDesconto(0.0);

        // Filmes do pacote Star Wars
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

        // Série Black Mirror
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

        // Filme avulso
        Filme bladeRunner = new Filme(
            "Blade Runner",
            30.0,
            criarTimeline(timelineBuilder, "blade_runner.mov")
        );

        // Superpacote
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
        System.out.println(
            "Produto: " + trilogiaMatrixAvulsa.getTitulo()
        );
        System.out.printf(
            "Preço com desconto: R$ %.2f%n",
            trilogiaMatrixAvulsa.getPreco()
        );

        System.out.println("\nSuperpacote:");
        System.out.println(
            "Produto: " + colecaoSciFi.getTitulo()
        );
        System.out.printf(
            "Preço total com desconto: R$ %.2f%n",
            colecaoSciFi.getPreco()
        );
        System.out.println(
            "Duração total: "
                + colecaoSciFi.getDuracao()
                + " segundos"
        );
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
}