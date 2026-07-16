package ifba.inf011.p3_2026_1.model.playlist;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	
	private List<PlaylistItem> items;
	
	public Playlist() {
		this.items = new ArrayList<PlaylistItem>();
	}

	public List<PlaylistItem> getItems() {
        return List.copyOf(this.items);
    }
	
	public void addItem(PlaylistItem item) {
		this.items.add(item);
	}

}
