package ifba.inf011.p2.s24_2.model;

public class Geolocalizacao {

	private String local;
	private Double latitude;
	private Double longitude;
	
	public static Geolocalizacao here() {
		return new Geolocalizacao("HERE", 0.0, 0.0);
	}
	
	public Geolocalizacao(String local, Double latitude, Double longitude) {
		this.local = local;
		this.latitude = latitude;
		this.longitude = longitude;
	}	
	
	public Geolocalizacao(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}	
	
	public Geolocalizacao(String local) {
		this.local = local;
	}		
	
	public String toString() {
		return this.local;
	}	
	
	public Double getLatitude() {
		return this.latitude;
	}
	
	public Double getLongitude() {
		return this.longitude;
	}
}
