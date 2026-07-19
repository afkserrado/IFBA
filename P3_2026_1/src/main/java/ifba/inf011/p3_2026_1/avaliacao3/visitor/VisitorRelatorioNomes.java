package ifba.inf011.p3_2026_1.avaliacao3.visitor;

import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Playlist;
import ifba.inf011.p3_2026_1.model.playlist.PlaylistItem;
import ifba.inf011.p3_2026_1.model.playlist.Video;

public class VisitorRelatorioNomes implements VisitorPlaylist {

    private final StringBuilder output;
    private int nivelIndentacao = 0;

    public VisitorRelatorioNomes() {
        this.output = new StringBuilder();
        this.nivelIndentacao = 0;
    }

    public String getOutput() {
        return output.toString();
    }

    @Override
    public void visit(Playlist playlist) {
        
        output.setLength(0);
        nivelIndentacao = 0;

        output.append("Playlist:\n");

        for (PlaylistItem item : playlist.getItens()) {
            item.accept(this);
        }
    }

    @Override
    public void visit(MP3 mp3) {
        this.indentar();
        output.append("- ")
                   .append(mp3.getNome())
                   .append(" (mp3)")
                   .append("\n");
    }

    @Override
    public void visit(Video video) {
        indentar();
        output.append("- ")
              .append(video.getNome())
              .append(" (vídeo)\n");
    }

    @Override
    public void visit(Filme filme) {
        indentar();
        output.append("- ")
              .append(filme.getTitulo())
              .append(" (filme)\n");
    }

    @Override
    public void visit(Episodio episodio) {
        indentar();
        output.append("- ")
              .append(episodio.getTitulo())
              .append(" (episódio)\n");
    }

    @Override
    public void visit(Serie serie) {
        
        indentar();
        output.append("- ")
              .append(serie.getTitulo())
              .append(" (série)\n");

        nivelIndentacao++;

        for(Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }

        nivelIndentacao--;
    }

    @Override
    public void visit(Pacote pacote) {
        
        indentar();
        output.append("- ")
              .append(pacote.getTitulo())
              .append(" (pacote)\n");

        nivelIndentacao++;

        for(PlaylistItem item : pacote.getProdutos()) {
            item.accept(this);
        }

        nivelIndentacao--;
    }

    private void indentar() {
        for(int i = 0; i < nivelIndentacao; i++) {
            output.append("\t");
        }
    }
}
