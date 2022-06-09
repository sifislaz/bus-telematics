package gr.upatras.bus.telematics.stop;
import gr.upatras.bus.telematics.json.JSONHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StopsClass {
	@JsonProperty("id")
	private final int stop_id;
	@JsonProperty("long")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
	
	
	
	
	
	public StopsClass(int stop_id,double longitude, double latitude) {
		super();
		this.stop_id=stop_id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getStop_id() {
		return stop_id;
	}
	
	
	
	
	

}
