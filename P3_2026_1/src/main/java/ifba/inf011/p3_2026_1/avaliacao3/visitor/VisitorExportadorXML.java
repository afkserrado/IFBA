package ifba.inf011.p3_2026_1.avaliacao3.visitor;

import ifba.inf011.p3_2026_1.avaliacao3.composite.ProdutoComponent;
import ifba.inf011.p3_2026_1.avaliacao3.validacao.ProdutoValidador;
import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Playlist;
import ifba.inf011.p3_2026_1.model.playlist.Video;

// Visitor concreto do Visitor
public class VisitorExportadorXML implements VisitorPlaylist {

    private final StringBuilder output;
    private int nivel;

    public VisitorExportadorXML() {
        this.output = new StringBuilder();
        this.nivel = 0;
    }

    public String export(Playlist playlist) {
        
        ProdutoValidador.validarObjeto(playlist);

        output.setLength(0);
        nivel = 0;

        output.append("<playlist>\n");
        nivel++;

        for (PlaylistItem item : playlist.getItems()) {
            item.accept(this);
        }

        nivel--;
        output.append("</playlist>\n");
        return output.toString();
    }

    @Override
    public void visit(MP3 mp3) {
        indentar();
        output.append("<mp3 nome=\"")
              .append(mp3.getNome())
              .append("\"/>\n");
    }

    @Override
    public void visit(Video video) {
        indentar();
        output.append("<video nome=\"")
              .append(video.getNome())
              .append("\" link=\"")
              .append(video.getLink())
              .append("\"/>\n");
    }

    @Override
    public void visit(Episodio episodio) {
        indentar();
        output.append("<episodio titulo=\"")
              .append(episodio.getTitulo())
              .append("\" numero=\"")
              .append(episodio.getNumero())
              .append("\"/>\n");
    }

    @Override
    public void visit(Filme filme) {
        indentar();
        output.append("<filme titulo=\"")
              .append(filme.getTitulo())
              .append("\"/>\n");
    }

    @Override
    public void visit(Serie serie) {

        indentar();
        output.append("<serie titulo=\"")
          .append(serie.getTitulo())
          .append("\" temporada=\"")
          .append(serie.getTemporada())
          .append("\">\n");

        nivel++;

        for(Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }

        nivel--;

        indentar();
        output.append("</serie>\n");
    }

    @Override
    public void visit(Pacote pacote) {
        
        indentar();
        output.append("<pacote titulo=\"")
              .append(pacote.getTitulo())
              .append("\">\n");

        nivel++;

        for(ProdutoComponent produto : pacote.getProdutos()) {
            if (produto instanceof PlaylistItem item) {
                item.accept(this);
            }
        }

        nivel--;
        
        indentar();
        output.append("</pacote>\n");
    }

    private void indentar() {
        for(int i = 0; i < nivel; i++) {
            output.append("\t");
        }
    }

}
