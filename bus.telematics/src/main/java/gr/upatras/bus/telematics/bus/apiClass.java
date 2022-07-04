package gr.upatras.bus.telematics.bus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author sotirissid
 *
 */
public class apiClass {
	// start point for api call
	private String origin;
	// end point of api call
	private String destination;
	// time required to get from origin to destination
	private String time;
	//route id of bus
	private int routeId;

	
	

	public apiClass(String origin, String destination, int routeId) throws IOException, InterruptedException, ParseException {
		super();
		this.origin = origin;
		this.destination = destination;
		this.routeId = routeId;
		this.time = this.api_call();
	}

	public apiClass(String origin, String destination) throws IOException, InterruptedException, ParseException {
		super();
		this.origin = origin;
		this.destination = destination;
		this.time = this.api_call();
	}
	
	public int getRouteId() {
		return routeId;
	}


	public String getTime() {
		return time;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String api_call() throws IOException, InterruptedException, ParseException {

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin=" + this.origin
						+ "&destination=" + this.destination + "&key=AIzaSyAnc0RzsD1qvac6KQl0G8JJmxNmd7H43ic"))
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String record = response.body();
		JSONObject object = (JSONObject) new JSONParser().parse(response.body());
		JSONArray routes = (JSONArray) object.get("routes");
		JSONObject route0 = (JSONObject) routes.get(0);
		JSONArray legs = (JSONArray) route0.get("legs");
		JSONObject legs0 = (JSONObject) legs.get(0);

		String duration = legs0.get("duration").toString();
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> tempMap = objectMapper.readValue(duration,
				new TypeReference<HashMap<String, String>>() {
				});
		// System.out.println(tempMap.get("text"));
		this.time = tempMap.get("text");
		return this.time;

	}
}
