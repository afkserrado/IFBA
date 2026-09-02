package ifba.inf011.p3_2026_1.model.playlist;

import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

// Interface Element do Visitor
public interface PlaylistItem {
	void accept(VisitorPlaylist visitor);
}
