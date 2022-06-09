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
}

