package gr.upatras.bus.telematics.users;


import java.util.Hashtable;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sotirissid
 *
 */

public class Users {
	@JsonProperty("id")
	private final int user_id;
	@JsonProperty("long")
	private double longitude;
	@JsonProperty("lat")
	private double latitude;
	
	
	
	public Users(int user_id, double longitude, double latitude) {
		super();
		this.user_id = user_id;
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


	public int getUser_id() {
		return user_id;
	}
	
	
	
	
	
}
