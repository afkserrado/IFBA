package ifba.inf011.p3_2026_1.avaliacao3.visitor;

import ifba.inf011.p3_2026_1.avaliacao3.util.ValidadorUtil;
import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Playlist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;
import ifba.inf011.p3_2026_1.model.playlist.Video;

public class VisitorLarguraBanda implements VisitorPlaylist {
    
    private static final String MSG_BANDWIDTH_INVALIDA =
        "A largura de banda por segundo não pode ser negativa.";

    private double larguraBandaTotal; // Largura de banda total da playlist
    private double larguraBandaPorSegundo;

    public VisitorLarguraBanda(double larguraBandaPorSegundo) {
        this.larguraBandaTotal = 0.0;
        ValidadorUtil.validarNaoNegativo(larguraBandaPorSegundo, MSG_BANDWIDTH_INVALIDA);
        this.larguraBandaPorSegundo = larguraBandaPorSegundo == 0.0 ? 1.5 : larguraBandaPorSegundo;
    }

    public void reset() {
        larguraBandaTotal = 0.0;
    }

    public double getLarguraBandaTotal() {
        return larguraBandaTotal;
    }

    @Override
    public void visit(Playlist playlist) {
        reset();

        for (PlaylistItem item : playlist.getItens()) {
            item.accept(this);
        }
    }

    @Override
    public void visit(MP3 mp3) {
        larguraBandaTotal += mp3.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Video video) {
        larguraBandaTotal += video.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Episodio episodio) {
        larguraBandaTotal += episodio.getDuracao() * this.larguraBandaPorSegundo;
    }

    @Override
    public void visit(Filme filme) {
        larguraBandaTotal += filme.getDuracao() * this.larguraBandaPorSegundo;
    }

    @Override
    public void visit(Serie serie) {
        larguraBandaTotal += serie.getDuracao() * this.larguraBandaPorSegundo;
    }

    @Override
    public void visit(Pacote pacote) {
        larguraBandaTotal += pacote.getDuracao() * this.larguraBandaPorSegundo;
    }
}
