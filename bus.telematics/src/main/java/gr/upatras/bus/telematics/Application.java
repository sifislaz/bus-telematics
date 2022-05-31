package gr.upatras.bus.telematics;
import gr.upatras.bus.telematics.stop.*;
import gr.upatras.bus.telematics.json.JSONHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		Stopsinit();
		
	}
	
	
	public static void Stopsinit() {
		// some stops in patras
		double[][] Stops = {{38.24681204011898, 21.735934713602745},{38.24757846022097, 21.737429748047255},{38.24945955695653, 21.739850911009736,38.254870592630446, 21.745601783219627},{38.26287031672199, 21.75107972735159},{38.28531985080199, 21.78661969872124}};
		JSONHandler js= new JSONHandler();
		List<StopsClass> list=new ArrayList<StopsClass>(); //creating a list of stops
		
		for(int i=0;i<Stops.length;i++) {
		StopsClass s= new StopsClass(1,Stops[i][0],Stops[i][1]);
		list.add(s);
		
		}
		//add the list to the json file
		js.createJSONFile("C:/Users/Sotiris/Desktop/stops.json",list); //change path to your liking
		
	}

}
