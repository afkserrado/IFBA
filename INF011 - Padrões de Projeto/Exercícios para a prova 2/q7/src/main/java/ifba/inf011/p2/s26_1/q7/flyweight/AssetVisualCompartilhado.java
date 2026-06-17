package ifba.inf011.p2.s26_1.q7.flyweight;

import java.util.Arrays;

// Concrete Flyweight
public class AssetVisualCompartilhado implements AssetVisual {

    private String nome;
    private String caminhoArquivo;
    private String resolucao;
    private byte[] dadosBinarios;

    public AssetVisualCompartilhado(String nome, String caminhoArquivo, String resolucao, byte[] dadosBinarios) {
        this.nome = nome;
        this.caminhoArquivo = caminhoArquivo;
        this.resolucao = resolucao;
        this.dadosBinarios = Arrays.copyOf(dadosBinarios, dadosBinarios.length);
    }

    @Override
    public String renderizar(int tempoInicio, int duracao, int posicaoX, int posicaoY, double escala, double opacidade) {
        return "[AssetVisual] "
                + this.nome
                + " | arquivo: "
                + this.caminhoArquivo
                + " | resolução: "
                + this.resolucao
                + " | tempo início: "
                + tempoInicio
                + "s | duração: "
                + duracao
                + "s | posição: ("
                + posicaoX
                + ", "
                + posicaoY
                + ") | escala: "
                + escala
                + " | opacidade: "
                + opacidade;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCaminhoArquivo() {
        return this.caminhoArquivo;
    }

    public String getResolucao() {
        return this.resolucao;
    }

    public byte[] getDadosBinarios() {
        return Arrays.copyOf(this.dadosBinarios, this.dadosBinarios.length);
    }
}