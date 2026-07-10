package ifba.inf011.p3_2026_1.model.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist{
	
	private List<PlaylistItem> items;
	
	public Playlist() {
		this.items = new ArrayList<PlaylistItem>();
	}
	
	public void addItem(PlaylistItem item) {
		this.items.add(item);
	}
	
	public String toXML() {
		
		String xml = this.items.stream()
                	.map(PlaylistItem::toXML)
                	.collect(Collectors.joining());
		return "<playlist>\n" 
				+ xml 
				+"</playlist>\n";
	}

	public Double getBandaTotal() {
	    return this.items.stream()
	                     .mapToDouble(item -> item.getBandwidth(PlaylistItem.BAND_PER_SECOND))
	                     .sum();
	}

	
}
