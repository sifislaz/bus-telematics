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
import gr.upatras.bus.telematics.route.*;


import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.Timer;

import gr.upatras.bus.telematics.simulation.*;

@SpringBootApplication
public class Application {

	private static boolean init = false;
	public static void main(String[] args) {
		if(init) {
//			stopInitializer();
			routeInitializer();
			
		}
		//busInitializer(10);
		SpringApplication.run(Application.class, args);
		//createDaemon();
	}
	
	private static void createDaemon() {
		Timer timer = new Timer();
		TimerTask sim = new startSimulation();
		timer.schedule(sim, 5000, 5000);
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
	
	public static void routeInitializer() {
		List<Route> routes = new ArrayList<Route>();
		List<LinkedHashMap> stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops201.json");
		LinkedList<Integer> route201Stops = new LinkedList<Integer>();
		LinkedList<Integer> route202Stops = new LinkedList<Integer>();
		LinkedList<Integer> route601Stops = new LinkedList<Integer>();
		LinkedList<Integer> route609Stops = new LinkedList<Integer>();
		LinkedList<Integer> route901Stops = new LinkedList<Integer>();
		LinkedList<Integer> route902Stops = new LinkedList<Integer>();
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route201Stops.add(id);
		}
		routes.add(new Route(201, "Sichena", "Papaflessa", route201Stops));
		
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops202.json");
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route202Stops.add(id);
		}
		routes.add(new Route(202, "Papaflessa", "Sichena", route202Stops));
		
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops601.json");
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route601Stops.add(id);
		}
		routes.add(new Route(601, "Center", "Abelokipi", route601Stops));
		
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops609.json");
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route609Stops.add(id);
		}
		routes.add(new Route(609, "Abelokipi", "Center", route609Stops));
		
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops901.json");
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route901Stops.add(id);
		}
		routes.add(new Route(901, "Center", "Agia", route901Stops));
		
		stopsJSON = (List<LinkedHashMap>) JSONHandler.readJSONFile("stops902.json");
		for(LinkedHashMap m : stopsJSON) {
			int id = Integer.parseInt(m.get("id").toString());
			route902Stops.add(id);
		}
		routes.add(new Route(902, "Agia", "Center", route902Stops));
		for(Route r:routes) {
			System.out.println(r.getId()+" "+ r.getStops());
		}
		JSONHandler.createJSONFile("routes.json", routes);
	}
	
}

class startSimulation extends TimerTask{
	public void run() {
		Simulation s = new Simulation();
		s.start();
	}
}
