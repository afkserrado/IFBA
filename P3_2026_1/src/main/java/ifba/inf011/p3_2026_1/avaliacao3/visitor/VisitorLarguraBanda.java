package ifba.inf011.p3_2026_1.avaliacao3.visitor;

import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;
import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Playlist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;
import ifba.inf011.p3_2026_1.model.playlist.Video;

public class VisitorLarguraBanda implements VisitorPlaylist {
    
    private double band;
    private double bandPerSecond;

    public VisitorLarguraBanda(double bandPerSecond) {
        
        this.band = 0.0;

        ProdutoValidador.validarNaoNegativo(bandPerSecond);

        this.bandPerSecond = bandPerSecond;
    }

    public void reset() {
        this.band = 0.0;
    }

    public double calcularLarguraBanda(Playlist playlist) {
        
        ProdutoValidador.validarObjeto(playlist);

        reset();

        for (PlaylistItem item : playlist.getItems()) {
            item.accept(this);
        }

        return this.band;
    }

    @Override
    public void visit(MP3 mp3) {
        this.band += mp3.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Video video) {
        this.band += video.getTamanhoMegaBytes();
    }

    @Override
    public void visit(Episodio episodio) {
        this.band += episodio.getDuracao() * this.bandPerSecond;
    }

    @Override
    public void visit(Filme filme) {
        this.band += filme.getDuracao() * this.bandPerSecond;
    }

    @Override
    public void visit(Serie serie) {
        this.band += serie.getDuracao() * this.bandPerSecond;
    }

    @Override
    public void visit(Pacote pacote) {
        this.band += pacote.getDuracao() * this.bandPerSecond;
    }
}
