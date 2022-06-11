package gr.upatras.bus.telematics.stop;

import java.util.Hashtable;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author jlaza
 *
 */
public class Stop {
	
	@JsonProperty("id")
	private final int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("long")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
	private static int ind = 1060;  // id of the last available stop
	
	
	public Stop(String name, double longitude, double latitude) {
		this.id = ++ind;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Stop(int id, String name, double longitude, double latitude) {
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * @return the stop's id
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * @param name
	 * 
	 * Set the name of the {@link Stop} instance
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the {@link Stop} instance
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param lng
	 * Set the stop's longitude
	 */
	public void setLongitude(double lng) {
		this.longitude = lng;
	}
	
	/**
	 * @return the stop's longitude
	 */
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * @param lat
	 * Set the stop's latitude
	 */
	public void setLatitude(double lat) {
		this.latitude = lat;
	}
	
	/**
	 * @return the stop's latitude
	 */
	public double getLatitude() {
		return this.latitude;
	}
	
}
