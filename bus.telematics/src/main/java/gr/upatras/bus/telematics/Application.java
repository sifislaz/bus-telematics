package gr.upatras.bus.telematics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import gr.upatras.bus.telematics.bus.*;
import gr.upatras.bus.telematics.json.JSONHandler;
import gr.upatras.bus.telematics.stop.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@SpringBootApplication
public class Application {
	private static boolean init = true;
	public static void main(String[] args) {
		if(init) {
			stopInitializer();
		}
		SpringApplication.run(Application.class, args);
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
}
