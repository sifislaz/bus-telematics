package gr.upatras.bus.telematics.bus;

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
public class Bus {
	@JsonProperty("id")
	private final int id;
	@JsonProperty("routeId")
	private int routeId = 0;
	@JsonProperty("long")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
	@JsonIgnore
	private static int ind = 1000;
	
	/**
	 * @param longitude
	 * @param latitude
	 * Initialize the {@link Bus} instance, with coordinates
	 */
	public Bus(double longitude, double latitude) {
		if(ind + 1 <=0) throw new IllegalStateException("Id should be positive");
		this.id = ++ind;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * @param id
	 * @param longitude
	 * @param latitude
	 * 
	 * Initialize the {@link Bus} instance, with id and coordinates
	 */
	public Bus(int id, double longitude, double latitude) {
		if(id <= 0) throw new IllegalArgumentException("Id should be positive");
		this.id = id;
		Bus.ind = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * @param longitude
	 * @param latitude
	 * @param routeId
	 * 
	 * Initialize the {@link Bus} instance, with coordinates and route ID
	 */
	public Bus(double longitude, double latitude, int routeId) {
		if(ind + 1 <=0) throw new IllegalStateException("Id should be positive");
		this.id = ++ind;
		this.longitude = longitude;
		this.latitude = latitude;
		this.routeId = routeId;
	}
	
	/**
	 * @param id
	 * @param longitude
	 * @param latitude
	 * @param routeId
	 * 
	 * Initialize the {@link Bus} instance, with id, coordinates and route ID
	 */
	public Bus(int id, double longitude, double latitude, int routeId) {
		if(id <= 0) throw new IllegalArgumentException("Id should be positive");
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.routeId = routeId;
	}
	
	/**
	 * @param routeId
	 * Set the id of the route this bus will follow
	 */
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	
	public int getRouteId() {
		return routeId;
	}
	
	/**
	 * @param longitude
	 * 
	 * Set the bus's longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * @param latitude
	 * 
	 * Set the bus's latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	/**
	 * @param x
	 * @param y
	 * 
	 * Change the coordinates of the bus
	 */
	public void moveTo(double x, double y) {
		this.setLongitude(x);
		this.setLatitude(y);
		
	}
	
	
}
