package ifba.inf011.p3_2026_1.model.playlist;

import java.util.ArrayList;
import java.util.List;

import ifba.inf011.p3_2026_1.avaliacao3.visitor.VisitorPlaylist;

public class Playlist implements PlaylistItem {
	
	private List<PlaylistItem> itens;
	
	public Playlist() {
		this.itens = new ArrayList<PlaylistItem>();
	}

	public List<PlaylistItem> getItens() {
        return List.copyOf(this.itens);
    }
	
	public void addItem(PlaylistItem item) {
		this.itens.add(item);
	}

	public void accept(VisitorPlaylist visitor) {
		visitor.visit(this);
	}

}
