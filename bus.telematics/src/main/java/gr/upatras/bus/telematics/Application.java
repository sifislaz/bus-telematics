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
import gr.upatras.bus.telematics.stop.StopsClass;

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
			busInitializer(10);
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

		public static void Stopsinit() {
		// some stops in patras
		double[][] Stops = {{38.24681204011898, 21.735934713602745},{38.24757846022097, 21.737429748047255},{38.24945955695653, 21.739850911009736,38.254870592630446, 21.745601783219627},{38.26287031672199, 21.75107972735159},{38.28531985080199, 21.78661969872124}};
		JSONHandler js= new JSONHandler();
		List<StopsClass> list=new ArrayList<StopsClass>(); //creating a list of stops
		
		for(int i=0;i<Stops.length;i++) {
		StopsClass s= new StopsClass(i,Stops[i][0],Stops[i][1]);
		list.add(s);
		
		}
		//add the list to the json file
		js.createJSONFile("C:/Users/Sotiris/Desktop/stops.json",list); //change path to your liking
		
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

