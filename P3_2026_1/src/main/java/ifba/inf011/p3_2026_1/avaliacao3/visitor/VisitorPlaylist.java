package ifba.inf011.p3_2026_1.avaliacao3.visitor;

import ifba.inf011.p3_2026_1.model.comercial.Episodio;
import ifba.inf011.p3_2026_1.model.comercial.Filme;
import ifba.inf011.p3_2026_1.model.comercial.Pacote;
import ifba.inf011.p3_2026_1.model.comercial.Serie;
import ifba.inf011.p3_2026_1.model.playlist.MP3;
import ifba.inf011.p3_2026_1.model.playlist.Video;

// Interface Visitor do Visitor
public interface VisitorPlaylist {
    void visit(MP3 mp3);
    void visit(Video video);
    void visit(Filme filme);
    void visit(Episodio episodio);
    void visit(Pacote pacote);
    void visit(Serie serie);
}
