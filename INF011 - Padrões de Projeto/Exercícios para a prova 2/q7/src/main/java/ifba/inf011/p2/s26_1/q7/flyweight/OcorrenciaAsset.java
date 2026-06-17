package ifba.inf011.p2.s26_1.q7.flyweight;

// Context
public class OcorrenciaAsset {

    private AssetVisual asset;

    private int tempoInicio;
    private int duracao;
    private int posicaoX;
    private int posicaoY;
    private double escala;
    private double opacidade;

    public OcorrenciaAsset(
            AssetVisual asset,
            int tempoInicio,
            int duracao,
            int posicaoX,
            int posicaoY,
            double escala,
            double opacidade
    ) {
        this.asset = asset;
        this.tempoInicio = tempoInicio;
        this.duracao = duracao;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.escala = escala;
        this.opacidade = opacidade;
    }

    public String renderizar() {
        return this.asset.renderizar(
                this.tempoInicio,
                this.duracao,
                this.posicaoX,
                this.posicaoY,
                this.escala,
                this.opacidade
        );
    }
}