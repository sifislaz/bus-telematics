package gr.upatras.bus.telematics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import gr.upatras.bus.telematics.bus.*;
import gr.upatras.bus.telematics.json.JSONHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@SpringBootApplication
public class Application {
	private static boolean init = false;
	public static void main(String[] args) {
		if(init) {
			busInitializer(10);
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
}

