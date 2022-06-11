package gr.upatras.bus.telematics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.upatras.bus.telematics.bus.*;
import gr.upatras.bus.telematics.json.JSONHandler;
import gr.upatras.bus.telematics.stop.*;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class Application {

	private static boolean init = false;
	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		if(init) {
			stopInitializer();
		}
		SpringApplication.run(Application.class, args);
		try {
			String s =api_call("patras","aigio");
		} catch (IOException | InterruptedException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param amount
	 */
	public static void busInitializer(int amount) {
		List<Bus> buses = new ArrayList<Bus>();
		double lng = 38.27151;
		double lat = 21.75699;
		
		for(int i=0; i<amount; i++) {
			buses.add(new Bus(lng,lat));
		}
		
		JSONHandler.createJSONFile("bus.json", buses);
		
	}

	public static void stopInitializer() {
		List<Stop> stops = new ArrayList<Stop>(); //creating a list of stops
		double[][] stopCoords = {{38.24063,21.730658},{38.241386,21.729435},{38.243265,21.731886},{38.24487,21.733962},{38.245913,21.735746}, {38.265875,21.756742}, {38.264841,21.758909}, {38.264328,21.760212}};
		String[] stopNames = {"Papaflessa", "Papaflessa-Korinthou", "Korinthou-Miaouli", "Votsi", "Georgiou Sq.", "Milichou 1", "Milichou 2", "Olympion"};
		
		for(int i=0; i<stopNames.length; i++) {
			stops.add(new Stop(stopNames[i], stopCoords[i][0], stopCoords[i][1]));
		}
		JSONHandler.createJSONFile("stops202.json", stops);
	}
		
	public static String api_call(String origin,String destination) throws IOException, InterruptedException, ParseException {
		

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://maps.googleapis.com/maps/api/directions/json?origin="+origin+"&destination="+destination+"&key=AIzaSyAnc0RzsD1qvac6KQl0G8JJmxNmd7H43ic"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String record=response.body();
		JSONObject object = (JSONObject) new JSONParser().parse(response.body());
		JSONArray routes = (JSONArray) object.get("routes");	
		JSONObject route0 = (JSONObject) routes.get(0);
		JSONArray  legs = (JSONArray) route0.get("legs");
		JSONObject legs0 = (JSONObject) legs.get(0);
		
		String duration = legs0.get("duration").toString();
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> tempMap = objectMapper.readValue(duration,
		            new TypeReference<HashMap<String, String>>() {
		            });
		System.out.println(tempMap.get("text"));
		return tempMap.get("text");
		 
	}
}
