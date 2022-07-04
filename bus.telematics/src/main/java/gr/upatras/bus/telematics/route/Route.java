package gr.upatras.bus.telematics.route;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.annotation.*;

import gr.upatras.bus.telematics.stop.*;

/**
 * @author jlaza
 *
 */
public class Route {
	@JsonProperty("id")
	private final int id;
	@JsonProperty("start")
	private String start;
	@JsonProperty("destination")
	private String destination;
	@JsonProperty("stops")
	private LinkedList<Integer> stopIds = new LinkedList<Integer>();
	
	public Route(int id, String start, String destination) {
		this.id = id;
		this.start = start;
		this.destination = destination;
	}
	
	public Route(int id, String start, String destination, LinkedList<Integer> stops) {
		this.id = id;
		this.start = start;
		this.destination = destination;
		this.stopIds = stops;
	}
	
	
	/**
	 * @return {@link Route} id
	 */
	public int getId() {
		return id;
	}
	
	
	
	/**
	 * @return the {@link LinkedList} of the {@link Stop} id this route has
	 */
	public LinkedList<Integer> getStops(){
		return stopIds;
	}
	
	/**
	 * @param stopIds
	 * Set the {@link LinkedList} of {@link Stop} instances
	 */
	public void setStops(LinkedList<Integer> stopIds) {
		this.stopIds = stopIds;
	}
	
	/**
	 * @param ind
	 * @param s
	 * 
	 * Add the No. ind {@link Stop} in the {@link Route}
	 */
	public void addStop(int ind, int s) {
		for(Integer temp : stopIds) {
			if(temp == s) {  // if it already exists, cancel
				break;
			}
			else {
				stopIds.add(ind, s);
			}
		}
	}
	
	/**
	 * @return the start of the {@link Route}
	 */
	public String getStart() {
		return start;
	}
	
	/**
	 * @param start
	 * 
	 * Set the start of the {@link Route}
	 */
	public void setStart(String start) {
		this.start = start;
	}
	
	/**
	 * @return the destination of the {@link Route}
	 */
	public String getDestination() {
		return destination;
	}
	
	/**
	 * @param destination
	 * 
	 * Set the destination of the {@link Route}
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
